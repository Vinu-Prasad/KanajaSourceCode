package com.kanaja.document.dao;

import com.kanaja.document.model.Author;

/**
 * 
 * @author vikas_c
 *
 */
public interface AuthorDAO {
	public Author getAuthorByName(String name);
	public Author getAuthorById(Integer authorId);
	public void saveAuthor(Author author);
	public void update(Author author);
	public void removeAuthor(Integer authorId);


}
