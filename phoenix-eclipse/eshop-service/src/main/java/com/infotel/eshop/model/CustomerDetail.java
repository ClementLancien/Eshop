package com.infotel.eshop.model;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity @Table(name="customer_detail")
public class CustomerDetail {
	@Id 
	private String email;
	
	@Column(name="birth_date")
	private LocalDate birthDate;
	private String phone;
	
	@OneToOne @JoinColumn(name="email") @MapsId
	private Customer customer;
	
	@Override
	public String toString() {
		return "CustomerDetail [email=" + email + ", birthDate=" + birthDate + ", phone=" + phone + ", customer="
				+ customer + "]";
	}

	public String getEmail() {
		return email;
	}

	public LocalDate getBirthDate() {
		return birthDate;
	}

	public String getPhone() {
		return phone;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setBirthDate(LocalDate birthDate) {
		this.birthDate = birthDate;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	
}
