package com.kanaja.document.dao;

import java.util.List;

import com.kanaja.document.model.Document;
import com.kanaja.search.helper.AdvancedSearchCriteria;
/**
 * 
 * @author vikas_c
 *
 */
public interface DocumentDAO {
	
	public void save(Document document);
	public Document getDocumentById(Integer documentId);
	public void remove(Integer documentId);
	public Integer getDocumentCount();
	public Document getDocumentByURL(String url);
	public List<Document> searchDocumentByCriteria(AdvancedSearchCriteria criteria);

}
