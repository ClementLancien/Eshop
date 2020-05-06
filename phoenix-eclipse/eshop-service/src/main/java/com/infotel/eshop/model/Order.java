package com.infotel.eshop.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderColumn;
import javax.persistence.Table;
import javax.persistence.Transient;


@Entity @Table(name="purchase_order")
public class Order {
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Enumerated(EnumType.STRING)
	private OrderStatus status;
	
	@Column(name = "order_number")
	private String orderNumber;
	
	@Transient
	private double totalAmount;
	
	@Column(name="order_date")
	private LocalDateTime dateTime;
	
	@ManyToOne @JoinColumn(name="customer_id")
	private Customer customer;
	
	@OneToMany(mappedBy = "order", cascade = CascadeType.ALL) //champ order dans orderline, mode cacade quand on insert les ordres on dit a JPA dajouter les OrderLines par defaut il ne le fait pas
	@OrderColumn(name = "position") // quand jpa fait insert dune ligne et lit le numéro de la ligne // supprimer for dans OrderServiceImpl
	protected List<OrderLine> lines;
	
	
	public void addLine(Product p, int quantity) {
		OrderLine line = new OrderLine();
		line.setProduct(p);
		line.setQuantity(quantity);
		line.setOrder(this);
		
		if (lines == null) {
			lines = new ArrayList<>();
		}
		lines.add(line);
	}
	
	
	public int getId() {
		return id;
	}
	
	public void calculTotalAmount() {
		for (OrderLine orderLine : lines) {
			totalAmount=0;
			totalAmount += orderLine.getTotalPriceProduct();
		}
	}

	public OrderStatus getStatus() {
		return status;
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
	
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	
	public void setLines(List<OrderLine> lines) {
		this.lines = lines;
	}

	public String getOrderNumber() {
		return orderNumber;
	}

	public void setOrderNumber(String orderNumber) {
		this.orderNumber = orderNumber;
	}
	
	public LocalDateTime getDateTime() {
		return dateTime;
	}

	public void setDateTime(LocalDateTime dateTime) {
		this.dateTime = dateTime;
	}


	@Override
	public String toString() {
		return "Order [id=" + id + ", status=" + status + ", orderNumber=" + orderNumber + ", totalAmount="
				+ totalAmount + ", dateTime=" + dateTime + ", customer=" + customer + ", lines=" + lines + "]";
	}

	
}
