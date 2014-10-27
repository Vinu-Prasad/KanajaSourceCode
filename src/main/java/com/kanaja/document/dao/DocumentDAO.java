package com.kanaja.document.dao;

import com.kanaja.document.model.Document;
/**
 * 
 * @author vikas_c
 *
 */
public interface DocumentDAO {
	
	public void save(Document document);
	public Document getDocumentById(Integer documentId);
	public void remove(Document document);
	

}
