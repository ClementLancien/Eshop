package com.infotel.eshop.dto;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="PlaceOrderLine")
@XmlAccessorType(XmlAccessType.FIELD)
public class PlaceOrderLineDto {
	
	@XmlElement(name="ProductId")
	private int productId;
	
	@XmlElement(name="Quantity")
	private int quantity;
	
	
	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public int getProductId() {
		return productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}


}
