package com.kanaja.document.model;

import java.util.Date;

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
@Table(name="DOCUMENT")
public class DocumentImpl implements Document {
	
	@Id
	@Column(name="DOCUMENT_ID")
	@GeneratedValue(strategy=GenerationType.AUTO)
	protected Integer id ;
	
	@Column(name="DOCUMENT_NAME")
	protected String name ;
	
	@Column(name="DOCUMENT_URL")
	protected String url ;
	
	@Column(name="PUBLISHED_DATE")
	protected Date publishedDate ;
	
	@Column(name="MODIFIED_DATE")
	protected Date modifiedDate ;
	
	@Column(name="INDEXED_DATE")
	protected Date indexedDate ;
	
	@Column(name="AUTHOR_NAME")
	protected String author ;
	
	@Column(name="TYPE")
	protected Boolean type ;

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

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public Boolean getType() {
		return type;
	}

	public void setType(Boolean type) {
		this.type = type;
	}

}
