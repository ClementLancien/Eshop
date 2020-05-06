package com.infotel.eshop.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.naming.NamingException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.infotel.eshop.exception.EShopException;
import com.infotel.eshop.model.ItemCounter;

public class ItemCounterDaoJdbc extends AbstractDaoJdbc implements ItemCounterDao {
	
	private final static Logger log = LogManager.getLogger(ItemCounterDaoJdbc.class);
	
	@Override
	public void create(ItemCounter counter) throws EShopException {
		String sql = "insert into item_counter (item, subset, next_value) "
				   + "values(?, ?, ?)";
		try (
				Connection cn = getConnection();
				PreparedStatement ps = cn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			) {
			ps.setString(1, counter.getItem());
			ps.setString(2, counter.getSubset());
			ps.setInt(3, counter.getNextValue());
			ps.executeUpdate();
			
			cn.commit();
		} catch (ClassNotFoundException | SQLException | NamingException e) {
			log.error("Problème accès base de données", e);
			throw new EShopException("Problème accès base de données", e);
			
		}
	}

	@Override
	public void update(ItemCounter counter) throws EShopException {
		String sql = "update item_counter set next_value = ? "
				   + "where item = ? and subset = ?";
		try (
				Connection cn = getConnection();
				PreparedStatement ps = cn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			) {
			ps.setInt(1, counter.getNextValue());
			ps.setString(2, counter.getItem());
			ps.setString(3, counter.getSubset());
			ps.executeUpdate();
			
			cn.commit();
		} catch (ClassNotFoundException | SQLException | NamingException e) {
			log.error("Problème accès base de données", e);
			throw new EShopException("Problème accès base de données", e);
		}
	}

	@Override
	public ItemCounter findByID(String item, String subset) throws EShopException {
		ItemCounter counter = null;
		
		String sql = "select item, subset, next_value from item_counter "
				   + "where item = ? and subset = ?";
		try (
				Connection cn = getConnection();
				PreparedStatement ps = cn.prepareStatement(sql);
			) {
			ps.setString(1, item);
			ps.setString(2, subset);
			try (ResultSet rs= ps.executeQuery();){
				if (rs.next()) {
					counter = new ItemCounter();
					counter.setItem(rs.getString(1));
					counter.setSubset(rs.getString(2));
					counter.setNextValue(rs.getInt(3));
				}
			}
		} catch (ClassNotFoundException | SQLException | NamingException e) {
			log.error("Problème accès base de données", e);
			throw new EShopException("Problème accès base de données", e);
		}
		return counter;
	}

}
