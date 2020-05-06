package com.infotel.eshop.service;

import java.util.List;

import com.infotel.eshop.exception.EShopException;
import com.infotel.eshop.model.Order;

public interface OrderService {
	
	void placeOrder(Order order) throws EShopException;
	
	List<Order> findOrdersByCustomer(String username) throws EShopException;
	
	List<Order> findOrdersToProcess();
}
