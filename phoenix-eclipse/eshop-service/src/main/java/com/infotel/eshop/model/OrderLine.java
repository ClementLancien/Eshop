package com.infotel.eshop.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity @Table(name="order_line")
public class OrderLine {
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private int quantity;
	
	@ManyToOne @JoinColumn(name="product_id")
	private Product product;
	
	@ManyToOne @JoinColumn(name="order_id")
	private Order order;
	
	public double getTotalPriceProduct() {
		return quantity * product.getPrice();
	}
	
	public void addQuantity(int quantity) {
		this.quantity += quantity; 
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

	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}

	
}
