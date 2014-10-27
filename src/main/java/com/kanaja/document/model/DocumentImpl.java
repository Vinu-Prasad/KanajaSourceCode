package com.kanaja.document.model;

import java.util.Date;
/**
 * 
 * @author vikas_c
 *
 */
public class DocumentImpl implements Document {
	protected Integer id ;
	protected String name ;
	protected String url ;
	protected Date publishedDate ;
	protected Date modifiedDate ;
	protected Date indexedDate ;

	public Integer getId() {
		
		return this.id;
	}

	public void setId(Integer id) {
		
		this.id = id ;
	}

	public String getName() {
		
		return this.name;
	}

	public void setName(String name) {
		
		this.name = name ;
	}

	public String getURL() {
		
		return this.url;
	}

	public void setURL(String url) {
		
		this.url = url ;
	}

	public Date getPublishedDate() {
		
		return this.publishedDate;
	}

	public void setPublishedDate(Date publishedDate) {
		
		this.publishedDate = publishedDate ;
	}

	public Date getModifiedDate() {
		
		return this.modifiedDate;
	}

	public void setModifiedDate(Date modifiedDate) {
		
		this.modifiedDate = modifiedDate ;
	}

	public Date getIndexedDate() {
		
		return this.indexedDate;
	}

	public void setIndexedDate(Date indexedDate) {
		
		this.indexedDate = indexedDate ;
	}

}
