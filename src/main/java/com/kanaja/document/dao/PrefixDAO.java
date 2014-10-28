package com.kanaja.document.dao;

import java.util.List;

import com.kanaja.document.model.Prefix;

/**
 * 
 * @author vikas_c
 *
 */
public interface PrefixDAO {

	public Integer save(Prefix prefix);
	public Prefix getPrefixById(Integer prefixId);
	public void remove(Prefix prefix);
	public List<Prefix> getDocumentPrefixList(Integer documentId);
	public Prefix getPrefixByName(String prefix);
	public void update(Prefix prefix);
}
