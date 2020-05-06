package com.infotel.eshop.dao;

import java.util.List;

import com.infotel.eshop.exception.EShopException;
import com.infotel.eshop.model.Order;
import com.infotel.eshop.model.OrderStatus;

public interface OrderDao {

	void create(Order order) throws EShopException;
	
	List<Order> findByCustomer(String username) throws EShopException;
	
	List<Order> findWithStatus(OrderStatus... statusList);
}
