package com.infotel.eshop.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.NamingException;

import com.infotel.eshop.exception.EShopException;
import com.infotel.eshop.model.Customer;
import com.infotel.eshop.model.Employee;
import com.infotel.eshop.model.User;

//@Component("userDAO")
//@Repository//("userDAO") // recherche par type
public class UserDaoJdbc extends AbstractDaoJdbc implements UserDao {

	
	@Override
	public User findByUsername(String username) throws EShopException {
		User user = null;
		
//		String sql = "select username, password, type, first_name, last_name, street, post_code, city, country from user_data where username = ?";
		String sql = "select username, password, type, first_name, last_name "
				+ "from user_data where username = ?";
		try (
				Connection cn = getConnection();
				PreparedStatement ps = cn.prepareStatement(sql);
			) {
			
			ps.setString(1, username);
			try(ResultSet rs= ps.executeQuery();) {
				if (rs.next()) {
					username = rs.getString(1);
					String password = rs.getString(2);
					String type = rs.getString(3);
					String firstName = rs.getString(4);
					String lastName = rs.getString(5); 
					
					switch (type) {
					case "C":
						Customer cust = new Customer();
						cust.setUsername(username);
						cust.setPassword(password);
						cust.setFirstName(firstName);
						cust.setLastName(lastName);
						
						user = cust;
						break;
						
					case "E":
						Employee emp = new Employee();
						emp.setUsername(username);
						emp.setPassword(password);
						
						user = emp;
						break;
					}
				}
			}
		} catch (ClassNotFoundException | SQLException | NamingException e) {
			throw new EShopException("Problème accès base de données", e);
		}
		return user;
	}

	@Override
	public void create(User user) throws EShopException {
		if (!(user instanceof Customer)) {
			return;
		}
		
		Customer cust = (Customer)user;
		String sql = "insert into user_data (username, password, type, first_name, last_name) "
				+ "values(?, ?, ?, ?, ?)";
		
		try (
				Connection cn = getConnection();
				PreparedStatement ps = cn.prepareStatement(sql);
			) {
			ps.setString(1, cust.getUsername());
			ps.setString(2, cust.getPassword());
			ps.setString(3, "C");
			ps.setString(4, cust.getFirstName());
			ps.setString(5, cust.getLastName());
			ps.executeUpdate();
			
			cn.commit();
		} catch (ClassNotFoundException | SQLException | NamingException e) {
			//if (cn != null) cn.rollback();
			//e.printStackTrace();
			throw new EShopException("Problème accès base de données", e);
		}
	}

}
