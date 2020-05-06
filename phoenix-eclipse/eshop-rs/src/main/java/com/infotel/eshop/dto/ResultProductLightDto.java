package com.infotel.eshop.dto;

import java.util.List;

public class ResultProductLightDto {

	private int sizeCollection;
	
	private List<ProductLightDto> products;

	public List<ProductLightDto> getProducts() {
		return products;
	}


	public void setProducts(List<ProductLightDto> products) {
		this.products = products;
	}

	public int getSizeCollection() {
		return sizeCollection;
	}

	public void setSizeCollection(int sizeCollection) {
		this.sizeCollection = sizeCollection;
	}
	
	
}
