package com.infotel.eshop.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.NamingException;

import com.infotel.eshop.exception.EShopException;
import com.infotel.eshop.model.ProductFamily;

public class ProductFamilyDaoJdbc extends AbstractDaoJdbc implements ProductFamilyDao {

	@Override
	public List<ProductFamily> findAll() throws EShopException {	
		List<ProductFamily> families = new ArrayList<>();
		
		String sql = "select id, name from product_family";
		try (
				Connection cn = getConnection();
				PreparedStatement ps = cn.prepareStatement(sql);
			) {
			
			try (ResultSet rs= ps.executeQuery();) {
				while(rs.next()) {
					ProductFamily f = new ProductFamily();
					f.setId(rs.getInt(1));
					f.setName(rs.getString(2));
					
					families.add(f);
				}
			}
		} catch (ClassNotFoundException | SQLException | NamingException e) {
			throw new EShopException("Problème accès base de données", e);
		}
		return families;
	}

}
