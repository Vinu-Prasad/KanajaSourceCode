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
	
	public Prefix getPrefix();
	public void setPrefix(Prefix prefix);
	public Document getDocument();
	public void getDocument(Document document);
	public Category getCategory();
	public void setCategory(Category category);
	public Author getAuthor();
	public void setAuthor(Author author);
	public Integer getPrefixFrequency();
	public void setPrefixFrequency(Integer pf);
	public Double getInverseDocumentFrequency();
	public void setInverseDocumentFrequency(Double idf);
	public Double getPfIdfScore();
	public void setPfIdfScore(Double pfIdfScore);
	public Boolean getType();
	public void setType(Boolean type);
	
	

}
