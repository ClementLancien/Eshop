package com.infotel.eshop.config;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration @Import(ServiceConfig.class)
public class WebConfig {

	@Bean
	public DataSource eshopDS() throws Exception {
		Context ctx = new InitialContext();
		DataSource ds = (DataSource) ctx.lookup("java:comp/env/jdbc/EShopDS");
		return ds;
	}
}
