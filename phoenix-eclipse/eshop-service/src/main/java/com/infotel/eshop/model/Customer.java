package com.infotel.eshop.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.OneToOne;

@Entity @DiscriminatorValue("C")
public class Customer extends User {
	@Column(name="first_name")
	private String firstName;
	
	@Column(name="last_name")
	private String lastName;
	
	@Embedded
	private Address address;

	@OneToOne(mappedBy="customer", cascade = CascadeType.ALL) //nom de la variable dans Customerdetail
	private CustomerDetail detail;
	
	public Customer() {
		super();
	}

	public Customer(String username, String password, String firstName, String lastName) {
		super(username, password);
		this.firstName = firstName;
		this.lastName = lastName;
	}

	@Override
	public String toString() {
		return "Customer [firstName=" + firstName + ", lastName=" + lastName + ", address=" + address + "]";
	}
	
	public String getFirstName() {
		return firstName;
	}
	
	public String getLastName() {
		return lastName;
	}
	
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public CustomerDetail getDetail() {
		return detail;
	}

	public void setDetail(CustomerDetail detail) {
		this.detail = detail;
	}
	
}
