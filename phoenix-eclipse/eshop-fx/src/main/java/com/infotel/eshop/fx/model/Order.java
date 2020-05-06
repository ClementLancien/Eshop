package com.infotel.eshop.fx.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Order {
	private int id;
	private OrderStatus status;
	private String orderNumber;
	private double totalAmount;
	private LocalDateTime dateTime;
	
	private Customer customer;
	private List<OrderLine> lines;
	
	public void addLine(OrderLine line) {
		if (lines == null) {
			lines = new ArrayList<>();
		}
		lines.add(line);
	}
	
	@Override
	public String toString() {
		return "Order [id=" + id + ", status=" + status + ", orderNumber=" + orderNumber + ", totalAmount="
				+ totalAmount + ", dateTime=" + dateTime + ", customer=" + customer + ", lines=" + lines + "]";
	}

	public int getId() {
		return id;
	}

	public OrderStatus getStatus() {
		return status;
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

	public Customer getCustomer() {
		return customer;
	}

	public List<OrderLine> getLines() {
		return lines;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setStatus(OrderStatus status) {
		this.status = status;
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

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public void setLines(List<OrderLine> lines) {
		this.lines = lines;
	}
}
