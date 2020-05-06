package com.infotel.eshop.dto;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="Products")
@XmlAccessorType(XmlAccessType.FIELD)
public class SearchProductLightDto {
	
	@XmlElement(name="Product")
	private List<ProductLightDto> products;

	public List<ProductLightDto> getProducts() {
		return products;
	}

	public void setProducts(List<ProductLightDto> products) {
		this.products = products;
	}

}
