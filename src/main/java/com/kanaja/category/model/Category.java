package com.kanaja.category.model;
/**
 * This interface class is for the various categories / sub categories 
 * available in the kanaja site
 * @author vikas_c
 *
 */
public interface Category {
	
	public Integer getId();
	
	public void setId(Integer id);
	
	public String getName();
	
	public void setName(String name);
	
	public Category getParentCategory();
	
	public void setParentCategory(Category parentCategory);
	

}
