package com.kanaja.search.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kanaja.category.dao.CategoryDAO;
import com.kanaja.document.dao.AuthorDAO;
import com.kanaja.document.dao.DocumentDAO;
import com.kanaja.document.dao.PrefixDAO;
import com.kanaja.document.dto.DocumentScore;
import com.kanaja.document.model.Document;
import com.kanaja.index.dao.IndexDAO;
import com.kanaja.index.model.Index;
import com.kanaja.search.helper.AdvancedSearchCriteria;
import com.kanaja.utils.StringUtility;
/**
 * 
 * @author vikas_c
 *
 */
@Service("searchService")
public class SearchService {
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
	public List<DocumentScore> search(String queryTerm ,AdvancedSearchCriteria criteria,String searchIn){
		List<String> termList = StringUtility.generateTokens(queryTerm);
		Map<Integer,Double> documentScoreMap = new HashMap<Integer,Double>();
		for(String qTerm : termList){
			List<String> prefixList = generateQueryPrefix(qTerm);
			Map<Integer,Double> prefixScoreMap = new HashMap<Integer,Double>();
			for(String prefix : prefixList){
				Double queryTruncationError = calculateQueryTruncationError(qTerm, prefix);
				List<Index> indexList = indexDAO.getIndexDetails(prefix, criteria,searchIn);
				for(Index index : indexList){
					Double individualScore = calculateIndividualDocumentScoreForPrefix(index.getPfIdfScore(), queryTruncationError);
					Double maxIndividualScore = prefixScoreMap.get(index.getPrefixPK().getDocument().getId()) ;
					if(maxIndividualScore==null){
						prefixScoreMap.put(index.getPrefixPK().getDocument().getId(), individualScore);
					}
					else if(maxIndividualScore < individualScore){
						prefixScoreMap.put(index.getPrefixPK().getDocument().getId(), individualScore);
					}
					
				}
			}
			Set<Integer> documents = prefixScoreMap.keySet() ;
			for(Integer docId : documents){
				if(documentScoreMap.get(docId)!=null){
					documentScoreMap.put(docId, documentScoreMap.get(docId)+prefixScoreMap.get(docId));
				}
				else{
					documentScoreMap.put(docId, prefixScoreMap.get(docId));
				}
			}
		}
		Set<Integer> documents = documentScoreMap.keySet() ;
		List<DocumentScore> documentScoreList = new ArrayList<DocumentScore>();
		for(Integer docId : documents){
			Document document = documentDAO.getDocumentById(docId);
			DocumentScore ds = new DocumentScore(document,documentScoreMap.get(docId));
			documentScoreList.add(ds);
		}
		Collections.sort(documentScoreList) ;	
		Collections.reverse(documentScoreList);
		return documentScoreList ;
	}
	
	@Transactional
	public List<DocumentScore> search(AdvancedSearchCriteria criteria){
		List<DocumentScore> documentScoreList = new ArrayList<DocumentScore>();
		List<Document> docList = documentDAO.searchDocumentByCriteria(criteria);
		for (Document doc : docList){
			DocumentScore ds = new DocumentScore(doc, 1.0);
			documentScoreList.add(ds);
		}
		return documentScoreList ;
	}
	protected List<String> generateQueryPrefix(String qTerm){
		List<String> prefixList = new ArrayList<String>();
		int currentLength = qTerm.length() ;
		int minimalLength = (int)Math.floor((1 - alpha) * currentLength) ;
		prefixList.add(qTerm);
		while(currentLength > minimalLength && currentLength >=tt ){
			qTerm = qTerm.substring(0,currentLength-1);
			prefixList.add(qTerm);
			currentLength = qTerm.length() ;
		}
		return prefixList ;
	}
		
	protected Double calculateQueryTruncationError(String term , String prefix){
		int termLength = term.length();
		int prefixLength = prefix.length();
		Double queryTruncationError = (double) (1 - (prefixLength/termLength));
		return queryTruncationError ;
	}
	
	protected Double calculateIndividualDocumentScoreForPrefix(Double pfIdfScore , Double queryTruncationError){
		return pfIdfScore * (1-queryTruncationError) ;
	}
	
	protected Double calculateIndependentScoreOfDocumentForQuery(List<Double> individualDocumentScore){
		Collections.sort(individualDocumentScore);
		return individualDocumentScore.get(0);
		
	}
	
	protected List<String> rankDocuments(){
		return null ;
	}

}
