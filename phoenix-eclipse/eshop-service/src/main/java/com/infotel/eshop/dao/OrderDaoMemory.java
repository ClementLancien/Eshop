package com.infotel.eshop.dao;

import java.util.ArrayList;
import java.util.List;

import com.infotel.eshop.model.Order;
import com.infotel.eshop.model.OrderStatus;

public class OrderDaoMemory implements OrderDao {

	private static Order[] ordersTable = new Order[100];
	private static int idCount = 1;
	
	@Override
	public void create(Order order) {
		order.setId(idCount++);
		for (int i = 0; i < ordersTable.length; i++) {
			if (ordersTable[i] == null) {
				ordersTable[i] = order;
				break;
			}
		}
	}

	@Override
	public List<Order> findByCustomer(String username) {
		List<Order> orders = new ArrayList<>();
		
		for (Order order : ordersTable) {
			if (order != null && order.getCustomer().getUsername().equals(username)) {
				orders.add(order);
			}
		}
		
		return orders;
	}

	@Override
	public List<Order> findWithStatus(OrderStatus... statusList) {
		return null;
	}


}
