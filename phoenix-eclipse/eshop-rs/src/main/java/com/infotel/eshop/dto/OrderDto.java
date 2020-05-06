package com.infotel.eshop.dto;

import java.time.LocalDateTime;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.infotel.eshop.jackson.LocalDateTimeDeserializer;
import com.infotel.eshop.jackson.LocalDateTimeSerializer;
import com.infotel.eshop.jaxb.LocalDateTimeAdapter;

@XmlRootElement(name="Order")
@XmlAccessorType(XmlAccessType.FIELD)
public class OrderDto {
	
	@XmlAttribute
	private int id;

	@XmlAttribute
	private String status;
	
	@XmlAttribute(name="number")
	private String orderNumber;
	
	@XmlElement(name="Customer")
	private CustomerDto customer;
	
	@XmlElement(name="TotalAmount")
	private double totalAmount;
	
	@XmlElement(name="Date")
	@XmlJavaTypeAdapter(LocalDateTimeAdapter.class)
	@JsonSerialize(using = LocalDateTimeSerializer.class)
	@JsonDeserialize(using = LocalDateTimeDeserializer.class)
	private LocalDateTime dateTime;
	
	@XmlElement(name="Line")
	@XmlElementWrapper(name="Lines")
	private List<OrderLineDto> lines;

	public void calculTotalAmount() {
		double sum = 0;
		for (OrderLineDto line : lines) {
			sum += line.getQuantity() * line.getProduct().getPrice();
		}
		totalAmount = sum;
	}

	public int getId() {
		return id;
	}

	public String getOrderNumber() {
		return orderNumber;
	}

	public double getTotalAmount() {
		return totalAmount;
	}

	public LocalDateTime getDateTime() {
		return dateTime;
	}

	public List<OrderLineDto> getLines() {
		return lines;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setOrderNumber(String orderNumber) {
		this.orderNumber = orderNumber;
	}

	public void setTotalAmount(double totalAmount) {
		this.totalAmount = totalAmount;
	}

	public void setDateTime(LocalDateTime dateTime) {
		this.dateTime = dateTime;
	}

	public void setLines(List<OrderLineDto> lines) {
		this.lines = lines;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public CustomerDto getCustomer() {
		return customer;
	}

	public void setCustomer(CustomerDto customer) {
		this.customer = customer;
	}

}
