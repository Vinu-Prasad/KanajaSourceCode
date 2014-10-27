package com.kanaja.document.model;
/**
 * 
 * @author vikas_c
 *
 */
public class AuthorImpl implements Author {
	
	protected Integer id ;
	protected String name ;
	protected Integer count ;

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
		
		this.name = name;
	}

	public Integer getDocumentCount() {
		
		return this.count;
	}

	public void setDocumentCount(Integer count) {
		
		this.count = count ;
	}

}
