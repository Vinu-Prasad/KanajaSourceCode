package com.kanaja.category.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * 
 * @author vikas_c
 *
 */
@Entity
@Table(name="CATEGORY")
public class CategoryImpl implements Category {
	
	@Id
	@Column(name="CATEGORY_ID")
	@GeneratedValue(strategy=GenerationType.AUTO)
	protected Integer id ;
	
	@Column(name="CATEGORY_NAME")
	protected String name ;
	
	@ManyToOne(targetEntity=CategoryImpl.class)
	@JoinColumn(name="PARENT_CATEGORY")
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
