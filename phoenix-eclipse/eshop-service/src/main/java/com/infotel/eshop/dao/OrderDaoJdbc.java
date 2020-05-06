package com.infotel.eshop.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.naming.NamingException;

import com.infotel.eshop.exception.EShopException;
import com.infotel.eshop.model.Customer;
import com.infotel.eshop.model.Order;
import com.infotel.eshop.model.OrderStatus;

public class OrderDaoJdbc extends AbstractDaoJdbc implements OrderDao {

	@Override
	public void create(Order order) throws EShopException {
		
		String sql = "insert into purchase_order (order_number, status, customer_id, order_date) "
				   + "values(?, ?, ?, ?)";
		try (
				Connection cn = getConnection();
				PreparedStatement ps = cn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			) {
			ps.setString(1, order.getOrderNumber());
//			ps.setString(2, "A");
			ps.setString(2, order.getStatus().name());
			ps.setString(3, order.getCustomer().getUsername());
			ps.setObject(4, order.getDateTime());
			ps.executeUpdate();
			
			try (ResultSet rs = ps.getGeneratedKeys();){
				if (rs.next()) {
					order.setId(rs.getInt(1));
				}
			}
			cn.commit();
			
		} catch (ClassNotFoundException | SQLException | NamingException e) {
			throw new EShopException("Problème accès base de données", e);
		}
	}

	@Override
	public List<Order> findByCustomer(String username) throws EShopException {
		List<Order> orders = new ArrayList<>();
		
		String sql = "select id, order_number, order_date, status, customer_id "
				   + "from purchase_order where customer_id = ?";
		try (
				Connection cn = getConnection();
				PreparedStatement ps = cn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			) {
			ps.setString(1, username);
			try (ResultSet rs= ps.executeQuery();) {
				while(rs.next()) {
					int id = rs.getInt(1);
					String orderNumber = rs.getString(2);
//					String trackingNumber = rs.getString(3);
					LocalDateTime date = rs.getTimestamp(3).toLocalDateTime();
					OrderStatus status = OrderStatus.valueOf(rs.getString(4));
					String custId = rs.getString(5);
					
					Order order = new Order();
					order.setId(id);
					order.setOrderNumber(orderNumber);
					order.setDateTime(date);
					order.setStatus(status);
					
					Customer cust = new Customer();
					cust.setUsername(custId);
					order.setCustomer(cust);
					
					orders.add(order);
				}
			}
			
		} catch (ClassNotFoundException | SQLException | NamingException e) {
			throw new EShopException("Problème accès base de données", e);
		}
		return orders;
	}


	@Override
	public List<Order> findWithStatus(OrderStatus... statusList) {
		return null;
	}
}
