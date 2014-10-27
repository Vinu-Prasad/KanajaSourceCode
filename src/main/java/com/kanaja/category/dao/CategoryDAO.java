package com.kanaja.category.dao;

import com.kanaja.category.model.Category;
/**
 * 
 * @author vikas_c
 *
 */
public interface CategoryDAO {
	
	public void save(Category category);
	public Category getCategoryById(Integer categoryId);
	public Category getParentCategory(Integer categoryId);
	public void remove(Integer categoryId);

}
