package com.infotel.eshop.fx.model;

public class Address {

	private String street;
	private String postCode;
	private String city;
	
	
	@Override
	public String toString() {
		return "Address [street=" + street + ", postCode=" + postCode + ", city=" + city + "]";
	}
	public String getStreet() {
		return street;
	}
	public String getPostCode() {
		return postCode;
	}
	public String getCity() {
		return city;
	}
	public void setStreet(String street) {
		this.street = street;
	}
	public void setPostCode(String postCode) {
		this.postCode = postCode;
	}
	public void setCity(String city) {
		this.city = city;
	}
	
	
}
