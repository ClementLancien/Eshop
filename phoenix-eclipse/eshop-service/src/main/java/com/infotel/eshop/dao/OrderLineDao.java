package com.infotel.eshop.dao;

import java.util.List;

import com.infotel.eshop.exception.EShopException;
import com.infotel.eshop.model.OrderLine;

public interface OrderLineDao {
	void create(OrderLine line, int position) throws EShopException;
	List<OrderLine> findByOrder(int orderId) throws EShopException;
}
