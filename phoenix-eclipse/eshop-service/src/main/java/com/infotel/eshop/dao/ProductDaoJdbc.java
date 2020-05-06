package com.infotel.eshop.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.naming.NamingException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.infotel.eshop.exception.EShopException;
import com.infotel.eshop.model.Product;

public class ProductDaoJdbc extends AbstractDaoJdbc implements ProductDao {
	
	private final static Logger log = LogManager.getLogger(ProductDaoJdbc.class);
	
	@Override
	public Map<String, Object>findByCriteria(String keyword, int familyId, int tagId, boolean randomize) throws EShopException {
	//public List<Product> findByCriteria(String keyword, int familyId, int tagId, boolean randomize) throws EShopException {
		List<Product> products = new ArrayList<>();
		
		String sql = "select id, name, price from product where 1 = 1";
		if (keyword != null && !keyword.isBlank()) {
			sql += " and name like ?";
		}
		
		if (familyId != -1) {
			sql += " and family_id = ?";
		}
		
		sql+= " limit 10"; // on fait de la pagination apres
		
		if (log.isDebugEnabled()) {
			log.debug("Requête sql pour la recherche de produit : " + sql);
		}
		
		try (Connection cn = getConnection();
			 PreparedStatement ps = cn.prepareStatement(sql);) {
			
			int count = 1;
			if (keyword != null && !keyword.isBlank()) {
				ps.setString(count++, "%" + keyword + "%");
			}
			
			if (familyId != -1) {
				ps.setInt(count++, familyId);
			}
			//ps.setMaxRows(10); trop long
			try (ResultSet rs= ps.executeQuery();) {
				while(rs.next()) {
					int id = rs.getInt(1);
					String name = rs.getString(2);
					double price = rs.getDouble(3);
					//LocalDate date = LocalDate.now();
					
					Product p = new Product();
					p.setId(id);
					p.setName(name);
					p.setPrice(price);
					//p.setReleaseDate(date);
					
					products.add(p);
				}
			}
//			System.out.println("CONNECTION : " + cn);
		} catch (ClassNotFoundException | SQLException | NamingException e) {
			throw new EShopException("Problème accès base de données", e);
		}
		return null;
		//return products;
	}

	@Override
	public Product findById(int id) throws EShopException {
		Product product = null;
		
		String sql = "select id, name, description, price from product where id = ?";
		try (Connection cn = getConnection();
			 PreparedStatement ps = cn.prepareStatement(sql);) {
			ps.setInt(1, id);
			
			try (ResultSet rs= ps.executeQuery();){
				if (rs.next()) {
					id = rs.getInt(1);
					String name = rs.getString(2);
					String description = rs.getString(3);
					double price = rs.getDouble(4);
					//LocalDate date = LocalDate.now();
					
					product = new Product();
					product.setId(id);
					product.setName(name);
					product.setDescription(description);
					product.setPrice(price);
					//product.setReleaseDate(date);
				}
			}
		} catch (ClassNotFoundException | SQLException | NamingException e) {
			throw new EShopException("Problème accès base de données", e);
		}
		return product;
	}

	@Override
	public List<Product> findByTag(int id) throws EShopException {
		// TODO Auto-generated method stub
		return null;
	}

}
