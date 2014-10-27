package com.kanaja.search.service;

import java.util.List;

import com.kanaja.category.dao.CategoryDAO;
import com.kanaja.document.dao.AuthorDAO;
import com.kanaja.document.dao.DocumentDAO;
import com.kanaja.document.dao.PrefixDAO;
import com.kanaja.index.dao.IndexDAO;
import com.kanaja.search.helper.AdvancedSearchCriteria;
/**
 * 
 * @author vikas_c
 *
 */
public class SearchService {
	
	protected IndexDAO indexDAO ;
	protected CategoryDAO categoryDAO ;
	protected DocumentDAO documentDAO ;
	protected PrefixDAO prefixDAO ;
	protected AuthorDAO authorDAO ;
	
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
	
	public List<String> search(String queryTerm ,AdvancedSearchCriteria criteria){
		return null ;
	}
	
	protected Double calculatePfIdfScore(Integer pf , Double idf){
		return (1 + pf)*(1+idf) ;
	}
	
	protected Double calculateQueryTruncationError(String term , String prefix){
		return null ;
	}
	
	protected Double calculateIndividualDocumentScoreForPrefix(Double pfIdfScore , Double queryTruncationError){
		return null ;
	}
	
	protected Double calculateIndependentScoreOfDocumentForQuery(List<Double> individualDocumentScore){
		return null ;
	}
	
	protected List<String> rankDocuments(){
		return null ;
	}

}
