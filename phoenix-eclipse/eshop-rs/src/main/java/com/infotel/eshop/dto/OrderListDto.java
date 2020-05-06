package com.infotel.eshop.dto;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="Orders")
@XmlAccessorType(XmlAccessType.FIELD)
public class OrderListDto {
	
//	@XmlElement(name="Customer")
//	private CustomerDto customer;
	
	@XmlElement(name="Order")
	private List<OrderDto> orders;

	public List<OrderDto> getOrders() {
		return orders;
	}

	public void setOrders(List<OrderDto> orders) {
		this.orders = orders;
	}

}
