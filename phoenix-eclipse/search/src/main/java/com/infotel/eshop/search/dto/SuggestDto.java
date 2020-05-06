package com.infotel.eshop.search.dto;

public class SuggestDto {
	private int id;
	private String name;
	private String type; // suggerer produit/catégorie ou autre chose
	
	public int getId() {
		return id;
	}
	
	public String getName() {
		return name;
	}
	
	public String getType() {
		return type;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public void setType(String type) {
		this.type = type;
	}

	
}
