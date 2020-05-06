package com.infotel.eshop.config;

import java.util.Properties;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.apache.http.HttpHost;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;


@Configuration @ComponentScan("com.infotel.eshop")
@EnableTransactionManagement // pr tx:annotation-driven
public class ServiceConfig {

	@Bean @Autowired
	public LocalContainerEntityManagerFactoryBean emf(DataSource datasource) {
		LocalContainerEntityManagerFactoryBean emf = new LocalContainerEntityManagerFactoryBean();
		emf.setDataSource(datasource);
		emf.setPackagesToScan("com.infotel.eshop.model");
		emf.setJpaVendorAdapter(vendorAdaptor());
		emf.setJpaProperties(jpaProperties());
		
		return emf;
	}
	//n'est pas un bean car on a fait qu'un usage local
	private HibernateJpaVendorAdapter vendorAdaptor() {
		return new HibernateJpaVendorAdapter();
	}
	
	private Properties jpaProperties() {
		Properties props = new Properties();
		props.setProperty("hibernate.show_sql", "true");
		props.setProperty("hibernate.format_sql", "true");
		
		return props;
	}
	
	@Bean @Autowired // pr le bean id="txManager" // obliger dappeler la methode par transactionManager sinon on ne peut pas utiliser @EnableTransactionManagement -> erreur a l'execution 
	public PlatformTransactionManager transactionManager(EntityManagerFactory emf) {
		JpaTransactionManager manager = new JpaTransactionManager();
		manager.setEntityManagerFactory(emf);
		return manager;
	}
	
	@Bean
	public RestHighLevelClient esClient() {
		return new RestHighLevelClient(
				RestClient.builder(
						new HttpHost("localhost", 9200, "http"),
						new HttpHost("localhost", 9201, "http")
						));		
	}
}
