package com.infotel.eshop.search.model;

import java.util.List;


public class Product {

	protected int id;
	protected String name;
	protected String description;
	protected double price;
	

	protected ProductFamily family;
	

	protected ProductImage image;
	

	protected List<ProductTag> tags;
	
	@Override
	public boolean equals(Object obj) {
		if(obj == null) return false;
		Product p = (Product)obj;
		return this.id == p.id;
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
	
	public double getPrice() {
		return price;
	}
	
	public void setPrice(double price) {
		this.price = price;
	}

	public String getDescription() {
		return description;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}

	public ProductFamily getFamily() {
		return family;
	}

	public void setFamily(ProductFamily family) {
		this.family = family;
	}

	public ProductImage getImage() {
		return image;
	}

	public void setImage(ProductImage image) {
		this.image = image;
	}

	public List<ProductTag> getTags() {
		return tags;
	}

	public void setTags(List<ProductTag> tags) {
		this.tags = tags;
	}
}