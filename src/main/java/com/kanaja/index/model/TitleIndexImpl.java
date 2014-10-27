package com.kanaja.index.model;

import com.kanaja.category.model.Category;
import com.kanaja.document.model.Author;
import com.kanaja.document.model.Document;
import com.kanaja.document.model.Prefix;

/**
 * 
 * @author vikas_c
 *
 */
public class TitleIndexImpl implements Index {

	protected Prefix prefix ;
	protected Document document ;
	protected Category category ;
	protected Author author ;
	protected Integer prefixFrequency ;
	protected Double inverseDocumentFrequency ;
	protected Double pfIdfScore;
	protected Boolean type ;

	public Prefix getPrefix() {
		
		return this.prefix;
	}

	public void setPrefix(Prefix prefix) {
		
		this.prefix = prefix ;
	}

	public Document getDocument() {
		
		return this.document;
	}

	public void getDocument(Document document) {
		
		this.document = document;
	}

	public Category getCategory() {
		
		return this.category;
	}

	public void setCategory(Category category) {
		
		this.category = category;
	}

	public Author getAuthor() {
		
		return this.author;
	}

	public void setAuthor(Author author) {
		
		this.author = author ;
	}

	public Integer getPrefixFrequency() {
		
		return this.prefixFrequency;
	}

	public void setPrefixFrequency(Integer pf) {
		
		this.prefixFrequency = pf ;
	}

	public Double getInverseDocumentFrequency() {
		
		return this.inverseDocumentFrequency ;
	}

	public void setInverseDocumentFrequency(Double idf) {
		
		this.inverseDocumentFrequency = idf ;
	}

	public Double getPfIdfScore() {
		
		return this.pfIdfScore;
	}

	public void setPfIdfScore(Double pfIdfScore) {
		
		this.pfIdfScore = pfIdfScore ;
	}

	public Boolean getType() {
		
		return this.type;
	}

	public void setType(Boolean type) {
		
		this.type = type ;
	}
}
