package com.infotel.eshop.dto;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="OrderLine")
@XmlAccessorType(XmlAccessType.FIELD)
public class OrderLineDto {
	
	@XmlAttribute
	private int id;
	
	@XmlElement(name="Product")
	private ProductLightDto product;
	
	@XmlElement(name="Quantity")
	private int quantity;
	
	
	public int getQuantity() {
		return quantity;
	}

	public ProductLightDto getProduct() {
		return product;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public void setProduct(ProductLightDto product) {
		this.product = product;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	
	
}
