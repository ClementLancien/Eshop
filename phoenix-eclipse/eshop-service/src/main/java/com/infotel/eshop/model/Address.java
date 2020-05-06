package com.infotel.eshop.model;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class Address {
	private String street;
	
	@Column(name="post_code")
	private String postcode;
	private String city;
	private String country;
	
	@Override
	public String toString() {
		return "Address [street=" + street + ", postcode=" + postcode + ", city=" + city + ", country=" + country + "]";
	}
	
	public String getStreet() {
		return street;
	}
	
	public String getPostcode() {
		return postcode;
	}
	
	public String getCity() {
		return city;
	}
	
	public String getCountry() {
		return country;
	}
	
	public void setStreet(String street) {
		this.street = street;
	}
	
	public void setPostcode(String postcode) {
		this.postcode = postcode;
	}
	
	public void setCity(String city) {
		this.city = city;
	}
	
	public void setCountry(String country) {
		this.country = country;
	}
	
}
