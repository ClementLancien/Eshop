package com.infotel.eshop.fx.model;

public class Customer {
	private String username;
	private String name;
	private Address address;
	
	@Override
	public String toString() {
		return "Customer [username=" + username + ", name=" + name + ", address=" + address + "]";
	}

	public String getUsername() {
		return username;
	}

	public String getName() {
		return name;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}
	
	
}
