package com.kanaja.document.model;

import java.util.Date;

/**
 * 
 * @author vikas_c
 *
 */
public interface Document {
	public Integer getId();
	public void setId(Integer id);
	public String getName();
	public void setName(String name);
	public String getURL();
	public void setURL(String url);
	public Date getPublishedDate();
	public void setPublishedDate(Date publishedDate);
	public Date getModifiedDate();
	public void setModifiedDate(Date modifiedDate);
	public Date getIndexedDate();
	public void setIndexedDate(Date indexedDate);
	public String getAuthor();
	public void setAuthor(String author);
	public Boolean getType();
	public void setType(Boolean type);

}
