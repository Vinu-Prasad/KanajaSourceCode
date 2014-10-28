package com.kanaja.document.dto;

import com.kanaja.document.model.Document;



public class DocumentScore implements Comparable<DocumentScore> {
	
	private Document document ;
	private Double score ;
	
	public DocumentScore(Document document ,Double score){
		this.document = document ;
		this.score = score ;
	}

	public Document getDocument() {
		return document;
	}

	public void setDocument(Document document) {
		this.document = document;
	}

	public Double getScore() {
		return score;
	}

	public void setScore(Double score) {
		this.score = score;
	}

	public int compareTo(DocumentScore o) {
		return this.score.compareTo(o.getScore());
	}

}
