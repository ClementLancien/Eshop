package com.infotel.eshop.dao;

import java.sql.Connection;
import java.sql.SQLException;

import javax.naming.NamingException;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

public abstract class AbstractDaoJdbc {
	
	@Autowired @Qualifier("eshopDS")
	private DataSource datasource;
	
//	public void setDatasource(DataSource datasource) {
//		this.datasource = datasource;
//	}
	
	protected Connection getConnection() throws ClassNotFoundException, SQLException, NamingException {
		return datasource.getConnection();
		//return getConnectionDM();
		//return getConnectionDS();
	}
	
//	protected Connection getConnectionDS() throws ClassNotFoundException, SQLException, NamingException { // DS = Data Source
//		Context context = new InitialContext();
//		DataSource ds = (DataSource)context.lookup("java:comp/env/jdbc/EShopDS");
//		
//		return ds.getConnection();
//	}
//	
//	protected Connection getConnectionDM() throws ClassNotFoundException, SQLException { //DM = Driver Manager
//		Class.forName("com.mysql.jdbc.Driver");
//		String url = "jdbc:mysql://localhost:3306/eshop?useSSL=false";
//		
//		Connection cn = DriverManager.getConnection(url, "scott", "tiger");
//		cn.setAutoCommit(false);
//		return cn;
//	}
}