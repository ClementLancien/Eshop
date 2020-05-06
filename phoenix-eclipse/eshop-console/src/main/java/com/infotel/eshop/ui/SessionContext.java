package com.infotel.eshop.ui;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.infotel.eshop.config.ConsoleConfig;
import com.infotel.eshop.model.Customer;
import com.infotel.eshop.model.OrderLine;
import com.infotel.eshop.model.Product;

public enum SessionContext {
	Instance;
	
	private Customer customer;
	private List<OrderLine> basket = new ArrayList<>(); // pour limiter
	
	public ResourceBundle bundle;
	
	public ExecutorService executor;
	
	public ApplicationContext springContext;
	
	public void init() {
		executor = Executors.newFixedThreadPool(3);
		bundle = ResourceBundle.getBundle("com.infotel.eshop.ui.i18n.Translate");
		//springContext = new ClassPathXmlApplicationContext("classpath:app-context-service.xml"); 
		springContext = new AnnotationConfigApplicationContext(ConsoleConfig.class); 
	}
	
	public void shutdown() {
		executor.shutdown();
	}
	
	public <T> T getBean(Class<T> clazz) {
		return springContext.getBean(clazz);
	}
	
	public void changeLanguage(String lang, String country) {
		Locale locale = new Locale(lang, country);
		bundle = ResourceBundle.getBundle("com.infotel.eshop.ui.i18n.Translate", locale);
	}
	
	public ResourceBundle getBundle() {
		return bundle;
	}

	public void addProductToBasket(Product product, int quantity) {
		// a completer
		
		for (OrderLine l : basket) {
			if(l.getProduct().equals(product)) {
				l.setQuantity(l.getQuantity() + quantity);
				return;
			}
		}
		
		OrderLine line = new OrderLine();
		line.setProduct(product);
		line.setQuantity(quantity); 
		basket.add(line);
		
	}
	
	public void clearBasket() {
		basket = new ArrayList<>(); // clear effet de bord si pas de modification dans orderline en list
	}
		
	public List<OrderLine> getBasket() {
		return basket;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	
}
