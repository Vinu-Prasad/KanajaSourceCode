package com.kanaja.search.helper;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class AdvancedSearchCriteria {
	
	public AdvancedSearchCriteria(String author, String type,
			String category, String fromDate , String toDate) throws ParseException {
		super();
		if(author!=null)
			this.author=author;
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
		if(type!=null){
			if(type.equalsIgnoreCase("prose"))
				this.type=true ;
			else if(type.equalsIgnoreCase("poetry"))
				this.type=false;
		}
		if(category!=null)
			this.categoryId = Integer.parseInt(category);
		if(fromDate!=null)
			this.fromDate = sdf.parse(fromDate) ;
		if(toDate!=null)
			this.toDate = sdf.parse(toDate) ;
	}
	private Integer categoryId;
	private String author ;
	private Boolean type ;
	private Date fromDate ;
	private Date toDate ;
	
	public Integer getCategoryId() {
		return categoryId;
	}
	public void setCategoryId(Integer categoryId) {
		this.categoryId = categoryId;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public Boolean getType() {
		return type;
	}
	public void setType(Boolean type) {
		this.type = type;
	}
	public Date getFromDate() {
		return fromDate;
	}
	public void setFromDate(Date date) {
		this.fromDate = date;
	}
	public Date getToDate() {
		return toDate;
	}
	public void setToDate(Date toDate) {
		this.toDate = toDate;
	}

}
