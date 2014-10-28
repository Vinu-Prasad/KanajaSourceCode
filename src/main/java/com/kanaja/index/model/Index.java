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
public interface Index {
	
	public PrefixPK getPrefixPK();
	public void setPrefixPK(PrefixPK prefixPk);
	public Author getAuthor();
	public void setAuthor(Author author);
	public Double getPrefixFrequency();
	public void setPrefixFrequency(Double pf);
	public Double getInverseDocumentFrequency();
	public void setInverseDocumentFrequency(Double idf);
	public Double getPfIdfScore();
	public void setPfIdfScore(Double pfIdfScore);
	public Boolean getType();
	public void setType(Boolean type);
	
	

}
