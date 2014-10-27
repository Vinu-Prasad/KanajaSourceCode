package com.kanaja.category.model;
/**
 * 
 * @author vikas_c
 *
 */
public class CategoryImpl implements Category {
	
	protected Integer id ;
	protected String name ;
	protected Category parentCategory ;

	public Integer getId() {
		 
		return this.id;
	}

	public void setId(Integer id) {
		 
		this.id = id ;
	}

	public String getName() {
		 
		return this.name;
	}

	public void setName(String name) {
		 
		this.name = name;
	}

	public Category getParentCategory() {
		 
		return this.parentCategory;
	}

	public void setParentCategory(Category parentCategory) {
		 
		this.parentCategory = parentCategory ;
	}

}
