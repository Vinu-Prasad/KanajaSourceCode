package com.kanaja.index.model;

import java.io.Serializable;

import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.kanaja.category.model.Category;
import com.kanaja.category.model.CategoryImpl;
import com.kanaja.document.model.Document;
import com.kanaja.document.model.DocumentImpl;
import com.kanaja.document.model.Prefix;
import com.kanaja.document.model.PrefixImpl;

@Embeddable
public class PrefixPK implements Serializable {
		
	private static final long serialVersionUID = 1L;
	@ManyToOne(targetEntity=PrefixImpl.class)
	@JoinColumn(name="PREFIX_ID")
	protected Prefix prefix ;
	@ManyToOne(targetEntity=DocumentImpl.class)
	@JoinColumn(name="DOCUMENT_ID")
	protected Document document ;
	@ManyToOne(targetEntity=CategoryImpl.class)
	@JoinColumn(name="CATEGORY_ID")
	protected Category category ;
	
	public Prefix getPrefix() {
		return prefix;
	}
	public void setPrefix(Prefix prefix) {
		this.prefix = prefix;
	}
	public Document getDocument() {
		return document;
	}
	public void setDocument(Document document) {
		this.document = document;
	}
	public Category getCategory() {
		return category;
	}
	public void setCategory(Category category) {
		this.category = category;
	}
	
	
	 
	
}
