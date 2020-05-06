package com.infotel.eshop.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.naming.NamingException;

import com.infotel.eshop.exception.EShopException;
import com.infotel.eshop.model.OrderLine;
import com.infotel.eshop.model.Product;

public class OrderLineDaoJdbc extends AbstractDaoJdbc implements OrderLineDao {

	@Override
	public void create(OrderLine line, int position) throws EShopException {
		String sql = "insert into order_line (position, quantity, order_id, product_id) "
				   + "values(?, ?, ?, ?)";
		try (
				Connection cn = getConnection();
				PreparedStatement ps = cn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			) {
			ps.setInt(1, position);
			ps.setInt(2, line.getQuantity());
			ps.setInt(3, line.getOrder().getId());
			ps.setInt(4, line.getProduct().getId());
			
			ps.executeUpdate();
			try (ResultSet rs = ps.getGeneratedKeys();){
				if (rs.next()) {
					line.setId(rs.getInt(1));
				}
			}
			cn.commit();
			
		} catch (ClassNotFoundException | SQLException | NamingException e) {
			throw new EShopException("Problème accès base de données", e);
		}
	}

	@Override
	public List<OrderLine> findByOrder(int orderId) throws EShopException {
		List<OrderLine> orderLines = new ArrayList<>();
		
		String sql = "select id, quantity, product_id "
				   + "from order_line where order_id = ? order by position";
		try (
				Connection cn = getConnection();
				PreparedStatement ps = cn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			) {
			ps.setInt(1, orderId);
			try (ResultSet rs= ps.executeQuery();) {
				while(rs.next()) {
					int id = rs.getInt(1);
					int quantity = rs.getInt(2);
					int productId = rs.getInt(3);
					
					Product p = new Product();
					p.setId(productId);
					
					OrderLine line = new OrderLine();
					line.setId(id);
					line.setQuantity(quantity);
					line.setProduct(p);
					
					orderLines.add(line);
				}
			}
		} catch (ClassNotFoundException | SQLException | NamingException e) {
			throw new EShopException("Problème accès base de données", e);
		}
		return orderLines;
	}

}
