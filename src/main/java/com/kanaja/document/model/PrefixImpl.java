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
@Table(name="PREFIX")
public class PrefixImpl implements Prefix {
	
	@Id
	@Column(name="PREFIX_ID")
	@GeneratedValue(strategy=GenerationType.AUTO)
	protected Integer id ;
	
	@Column(name="PREFIX")
	protected String prefix ;
	
	@Column(name="DOCUMENT_FREQUENCY")
	protected Integer documentFrequency ;

	public Integer getId() {
		
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id ;

	}

	public String getPrefix() {
		
		return this.prefix;
	}

	public void setPrefix(String prefix) {
		
		this.prefix = prefix ;
	}

	public Integer getDocumentFrequency() {
		
		return this.documentFrequency;
	}

	public void setDocumentFrequency(Integer df) {
		
		this.documentFrequency = df ;
	}

}
