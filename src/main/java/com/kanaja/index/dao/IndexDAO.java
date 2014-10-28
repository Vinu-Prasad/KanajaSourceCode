package com.kanaja.index.dao;

import java.util.List;

import com.kanaja.index.model.Index;
import com.kanaja.search.helper.AdvancedSearchCriteria;

public interface IndexDAO {
	
	public void save(Index index);
	public List<Index> getIndexDetails(String prefix , AdvancedSearchCriteria criteria,String searchIn);
	public void remove(Integer indexId) ;
	public List<String> getPrefixByDocument(Integer documentId);
	public void removeIndexByDocument(Integer documentId);
	public List<String> getTitlePrefixByDocument(Integer documentId);
	public void removeTitleIndexByDocument(Integer documentId);
}
