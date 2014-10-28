package com.kanaja.document.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 
 * @author vikas_c
 *
 */
@Entity
@Table(name="AUTHOR")
public class AuthorImpl implements Author {
	
	@Id
	@Column(name="AUTHOR_ID")
	@GeneratedValue(strategy=GenerationType.AUTO)
	protected Integer id ;
	
	@Column(name="AUTHOR_NAME")
	protected String name ;
	
	@Column(name="DOC_COUNT")
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
