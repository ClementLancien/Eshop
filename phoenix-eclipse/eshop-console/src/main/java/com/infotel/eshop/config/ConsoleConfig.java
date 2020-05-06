package com.infotel.eshop.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import com.infotel.eshop.config.ServiceConfig;

@Configuration @Import(ServiceConfig.class)
@PropertySource(value="file:c:/Dev/jdbc.properties")
public class ConsoleConfig {
	
	@Bean
	public DataSource eshopDS(
			@Value("${jdbc.driver}") String driver,
			@Value("${jdbc.url}") String  url,
			@Value("${jdbc.username}") String  username,
			@Value("${jdbc.password}") String password
			) {
		DriverManagerDataSource datasource = new DriverManagerDataSource();
		datasource.setDriverClassName(driver);//"com.mysql.jdbc.Driver");
		datasource.setUrl(url);//"jdbc:mysql://localhost:3306/eshop?useSSL=false");
		datasource.setUsername(username);//"scott");
		datasource.setPassword(password);//"tiger");
		
		return datasource;
	}
}
