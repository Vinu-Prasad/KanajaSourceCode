package com.kanaja.index.service;

import java.util.List;

import com.kanaja.category.dao.CategoryDAO;
import com.kanaja.document.dao.AuthorDAO;
import com.kanaja.document.dao.DocumentDAO;
import com.kanaja.document.dao.PrefixDAO;
import com.kanaja.document.model.Prefix;
import com.kanaja.index.dao.IndexDAO;
import com.kanaja.parser.service.ParserService;
/**
 * 
 * @author vikas_c
 *
 */
public class IndexService {
	
	protected IndexDAO indexDAO ;
	protected CategoryDAO categoryDAO ;
	protected DocumentDAO documentDAO ;
	protected PrefixDAO prefixDAO ;
	protected AuthorDAO authorDAO ;
	protected ParserService parserService ;
	
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
	
	public void setParserService(ParserService parserService) {
		this.parserService = parserService;
	}

	public boolean IndexDocument(String documentName , String url){
		return false ;
	}
	
	protected List<Prefix> generatePrefix(String term){
		return null ;
	}
	
	protected List<String> generateTokens(String url){
		return null ;
	}
	
	protected Integer calculatePrefixFrequency(String prefix){
		return null ;
	}
	
	protected Double calculateInverseDocumentFrequency(String prefix){
		return null ;
	}
	
	

}
