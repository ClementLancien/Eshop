package com.infotel.eshop.search.model;

public class ProductFamily {

	private int id;
	private String name;
	
	@Override
	public String toString() {
		return "ProductFamily [id=" + id + ", name=" + name + "]";
	}

	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}
	
}
