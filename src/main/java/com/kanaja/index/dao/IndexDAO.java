package com.kanaja.index.dao;

import com.kanaja.index.model.Index;
import com.kanaja.search.helper.AdvancedSearchCriteria;

public interface IndexDAO {
	
	public void save(Index index);
	public Index getIndexDetails(String prefix , AdvancedSearchCriteria criteria);
	public void remove(Index index) ;
	
}
