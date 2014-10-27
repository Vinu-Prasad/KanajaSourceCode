package com.kanaja.document.model;
/**
 * 
 * @author vikas_c
 *
 */
public interface Author {
	
	public Integer getId();
	public void setId(Integer id);
	public String getName();
	public void setName(String name);
	public Integer getDocumentCount();
	public void setDocumentCount(Integer count);

}
