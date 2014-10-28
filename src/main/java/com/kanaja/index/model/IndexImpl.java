package com.kanaja.index.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.kanaja.category.model.Category;
import com.kanaja.category.model.CategoryImpl;
import com.kanaja.document.model.Author;
import com.kanaja.document.model.AuthorImpl;
import com.kanaja.document.model.Document;
import com.kanaja.document.model.DocumentImpl;
import com.kanaja.document.model.Prefix;
import com.kanaja.document.model.PrefixImpl;

/**
 * 
 * @author vikas_c
 *
 */
@Entity
@Table(name="DOCUMENT_INDEX")
public class IndexImpl implements Index {
	

	@EmbeddedId
	protected PrefixPK prefixPK ;
	@ManyToOne(targetEntity=AuthorImpl.class)
	@JoinColumn(name="AUTHOR_ID")
	protected Author author ;
	@Column(name="PREFIX_FREQUENCY")
	protected Double prefixFrequency ;
	@Column(name="INVERSE_DOCUMENT_FREQUENCY")
	protected Double inverseDocumentFrequency ;
	@Column(name="PF_IDF_SCORE")
	protected Double pfIdfScore;
	@Column(name="TYPE")
	protected Boolean type ;
	
	public PrefixPK getPrefixPK() {
		return prefixPK;
	}

	public void setPrefixPK(PrefixPK prefixPK) {
		this.prefixPK = prefixPK;
	}

	public Author getAuthor() {
		
		return this.author;
	}

	public void setAuthor(Author author) {
		
		this.author = author ;
	}

	public Double getPrefixFrequency() {
		
		return this.prefixFrequency;
	}

	public void setPrefixFrequency(Double pf) {
		
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


