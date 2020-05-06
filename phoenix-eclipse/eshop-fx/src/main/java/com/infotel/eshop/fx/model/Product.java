package com.infotel.eshop.fx.model;

public class Product {
	private int id;
	private String name;
	
	@Override
	public String toString() {
		return "Product [id=" + id + ", name=" + name + "]";
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
