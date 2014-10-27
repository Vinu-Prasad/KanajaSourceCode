package com.kanaja.document.model;
/**
 * 
 * @author vikas_c
 *
 */
public class PrefixImpl implements Prefix {
	
	protected Integer id ;
	protected String prefix ;
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
