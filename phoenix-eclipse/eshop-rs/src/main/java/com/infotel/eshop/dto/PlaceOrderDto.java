package com.infotel.eshop.dto;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="PlaceOrder")
@XmlAccessorType(XmlAccessType.FIELD)
public class PlaceOrderDto {
	
	@XmlElement(name="Customer")
	private String customer;
	
	@XmlElement(name="Line")
	@XmlElementWrapper(name="Lines")
	private List<PlaceOrderLineDto> lines;

	public List<PlaceOrderLineDto> getLines() {
		return lines;
	}

	public void setLines(List<PlaceOrderLineDto> lines) {
		this.lines = lines;
	}

	public String getCustomer() {
		return customer;
	}

	public void setCustomer(String customer) {
		this.customer = customer;
	}

}
