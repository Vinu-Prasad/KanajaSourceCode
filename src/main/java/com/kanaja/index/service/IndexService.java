package com.kanaja.index.service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kanaja.category.dao.CategoryDAO;
import com.kanaja.category.model.Category;
import com.kanaja.category.model.CategoryImpl;
import com.kanaja.document.dao.AuthorDAO;
import com.kanaja.document.dao.DocumentDAO;
import com.kanaja.document.dao.PrefixDAO;
import com.kanaja.document.model.Author;
import com.kanaja.document.model.AuthorImpl;
import com.kanaja.document.model.Document;
import com.kanaja.document.model.DocumentImpl;
import com.kanaja.document.model.Prefix;
import com.kanaja.document.model.PrefixImpl;
import com.kanaja.index.dao.IndexDAO;
import com.kanaja.index.model.Index;
import com.kanaja.index.model.IndexImpl;
import com.kanaja.index.model.PrefixPK;
import com.kanaja.index.model.TitleIndexImpl;
import com.kanaja.utils.StringUtility;
/**
 * 
 * @author vikas_c
 *
 */
@Service("indexService")
public class IndexService {
	@Resource(name="indexDAO")
	protected IndexDAO indexDAO ;
	@Resource(name="categoryDAO")
	protected CategoryDAO categoryDAO ;
	@Resource(name="documentDAO")
	protected DocumentDAO documentDAO ;
	@Resource(name="prefixDAO")
	protected PrefixDAO prefixDAO ;
	@Resource(name="authorDAO")
	protected AuthorDAO authorDAO ;
	@Value("${alpha}")
	protected double alpha;
	@Value("${tt}")
	protected int tt ;
	
		
	public void setCategoryDAO(CategoryDAO categoryDAO) {
		this.categoryDAO = categoryDAO;
	}

	public void setDocumentDAO(DocumentDAO documentDAO) {
		this.documentDAO = documentDAO;
	}

	public void setPrefixDAO(PrefixDAO prefixDAO) {
		this.prefixDAO = prefixDAO;
	}

	public void setAuthorDAO(AuthorDAO authorDAO) {
		this.authorDAO = authorDAO;
	}
	
	public void setIndexDAO(IndexDAO _indexDAO){
		this.indexDAO = _indexDAO ;
	}
	
	
	@Transactional
	public boolean IndexDocument(String documentName , String content, String url, Date publishedDate,boolean type,String authorName,String [] categoryList){
		List<String> termList = StringUtility.generateTokens(content);
		List<String> titleTermList = StringUtility.generateTokens(documentName);		
		Document document = new DocumentImpl();
		document.setURL(url);
		document.setName(documentName);
		document.setIndexedDate(new Date());
		document.setModifiedDate(new Date());
		document.setPublishedDate(publishedDate);
		document.setAuthor(authorName);
		document.setType(type);
		documentDAO.save(document);
		
		Author author = updateAuthorDetails(authorName);
		
		generateIndex(termList, document, author, "CONTENT", type,categoryList);
		generateIndex(titleTermList,document,author,"TITLE",type,categoryList);
				
 		return true ;
	}
	
	@Transactional
	public boolean removeDocument(Document document,String authorName){
		List<String> prefixList = indexDAO.getPrefixByDocument(document.getId());
		List<String> titlePrefixList = indexDAO.getTitlePrefixByDocument(document.getId());
		prefixList.addAll(titlePrefixList);
		indexDAO.removeIndexByDocument(document.getId());
		indexDAO.removeTitleIndexByDocument(document.getId());
		for(String prefix:prefixList){
			Prefix p  = prefixDAO.getPrefixByName(prefix);
			int df = p.getDocumentFrequency() ;
			if(df == 1){
				prefixDAO.remove(p);
			}
			else{
				p.setDocumentFrequency(df - 1);
				prefixDAO.update(p);
			}
		}
		
		documentDAO.remove(document.getId());
		// remove document pending
		Author a = authorDAO.getAuthorByName(authorName);
		if(a!=null){
			int dc = a.getDocumentCount() ;
			if(dc == 1){
				authorDAO.removeAuthor(a.getId());
			}
			else{
				a.setDocumentCount(dc-1);
				authorDAO.update(a);
			}
		}
			
		return true ;
	}
	
	
	
	protected void generatePrefix(String term,Map<String,Integer> prefixCountMap){
		int currentLength = term.length() ;
		int minimalLength = (int)Math.floor((1 - alpha) * currentLength) ;
		addToMap(term,prefixCountMap);
		while(currentLength > minimalLength && currentLength >=tt ){
			term = term.substring(0,currentLength-1);
			addToMap(term,prefixCountMap);
			currentLength = term.length() ;
		}
	
	}
	
	protected void addToMap(String prefix,Map<String,Integer> prefixCountMap){
		if(prefixCountMap.get(prefix)!=null)
			prefixCountMap.put(prefix, prefixCountMap.get(prefix)+1);
		else
			prefixCountMap.put(prefix, 1);
	}
	
	protected void generateIndex(List<String> termList,Document document,Author author,String indexType,Boolean type,String [] categoryList){
		Map<String,Integer> prefixCountMap = new HashMap<String, Integer>() ;
		Double pf ;
		Double idf ;
		Double pfIdfScore ;
		
		for(String term:termList){
			generatePrefix(term,prefixCountMap);
		}
		
		Set<String> prefixSet = prefixCountMap.keySet() ;
		for(String prefix:prefixSet){
			Prefix p = prefixDAO.getPrefixByName(prefix);
			if(p!=null){
				p.setDocumentFrequency(p.getDocumentFrequency()+1);
				prefixDAO.update(p);
			}
			else{
				p = new PrefixImpl();
				p.setPrefix(prefix);
				p.setDocumentFrequency(1);
				prefixDAO.save(p);
			}
			for(String c : categoryList){
				Category category = categoryDAO.getCategoryByName(c.trim());
				if(category==null){
					category = new CategoryImpl();
					category.setName(c.trim());
					category.setParentCategory(null);
					categoryDAO.save(category);
				}
				Index index = getIndex(indexType);
				
				PrefixPK prefixPk = new PrefixPK() ;
				prefixPk.setPrefix(p);prefixPk.setDocument(document);prefixPk.setCategory(category);
				index.setPrefixPK(prefixPk);
				index.setAuthor(author);
				pf = calculatePrefixFrequency(prefix, prefixCountMap) ;
				
				idf = calculateInverseDocumentFrequency(documentDAO.getDocumentCount(), p.getDocumentFrequency());
				pfIdfScore = calculatePfIdfScore(pf, idf) ;
				index.setPrefixFrequency(pf);
				index.setInverseDocumentFrequency(idf);
				index.setPfIdfScore(pfIdfScore);
				index.setType(type);
				indexDAO.save(index);
			}
		}
		
	}
	protected Index getIndex(String indexType){
		if(indexType.equalsIgnoreCase("TITLE")){
			return new TitleIndexImpl() ;
		}
		else
			return new IndexImpl();
	}
	
	protected Author updateAuthorDetails(String authorName){
		Author author = authorDAO.getAuthorByName(authorName);
		if(author!=null){
			author.setDocumentCount(author.getDocumentCount()+1);
			authorDAO.update(author);
		}
		else{
			author = new AuthorImpl();
			author.setName(authorName);
			author.setDocumentCount(1);
			authorDAO.saveAuthor(author);
		}
		return author ;
	}
	
	protected Double calculatePrefixFrequency(String prefix,Map<String,Integer> prefixCountMap){
		return Math.log(1+prefixCountMap.get(prefix));
	}
	
	protected Double calculateInverseDocumentFrequency(Integer totalDocuments, Integer documentWithPrefixCount){
		double value = (double) totalDocuments/(double) Math.abs(documentWithPrefixCount) ;
		return Math.log(value) ;
	}
	
	protected Double calculatePfIdfScore(Double pf , Double idf){
		return (1 + pf)*(1+idf) ;
	}
	
	@Transactional
	public String saveCategory(Category c){
		categoryDAO.save(c);
		return "success" ;
	}
	@Transactional
	public Category getCategory(Integer id){
		return categoryDAO.getCategoryById(id) ;
	}
	
	@Transactional
	public Document getDocumentByURL(String url){
		return documentDAO.getDocumentByURL(url);
	}

}
