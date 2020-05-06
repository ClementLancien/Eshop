package com.infotel.eshop.fx.model;

public class OrderLine {
	private int id;
	private int quantity;
	private Product product;
	
	@Override
	public String toString() {
		return "OrderLine [id=" + id + ", quantity=" + quantity + ", product=" + product + "]";
	}

	public int getId() {
		return id;
	}
	
	public int getQuantity() {
		return quantity;
	}
	
	public Product getProduct() {
		return product;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
	public void setProduct(Product product) {
		this.product = product;
	}
}
