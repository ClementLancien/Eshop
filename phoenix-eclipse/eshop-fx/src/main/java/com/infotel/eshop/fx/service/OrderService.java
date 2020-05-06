package com.infotel.eshop.fx.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.infotel.eshop.fx.model.Customer;
import com.infotel.eshop.fx.model.Order;
import com.infotel.eshop.fx.model.OrderLine;
import com.infotel.eshop.fx.model.OrderStatus;
import com.infotel.eshop.fx.model.Product;
import com.infotel.eshop.fx.service.xml.OrdersResponseToProcessReaderSax;
import com.infotel.eshop.fx.service.xml.OrdersResponseToProcessReaderWS;

public class OrderService {

	private final static Logger log = LogManager.getLogger(OrderService.class);
	
	public List<Order> findOrdersToProcess() {
//		OrdersResponseToProcessReaderSax reader =
		//OrdersResponseToProcessReaderSax reader = new OrdersResponseToProcessReaderSax();
		//OrdersResponseToProcessReaderDom4j reader = new OrdersResponseToProcessReaderDom4j();
		OrdersResponseToProcessReaderWS reader = new OrdersResponseToProcessReaderWS();
		try {
			return reader.getResponse();
//			System.out.println(reader.getResponse());
		} catch (Exception e) {
			log.error("Echec du reader de l'authentification de l'utilisateur", e);
			//e.printStackTrace(); // a remplacer par un log
			return null;
		}
	}
	
	@Deprecated
	public List<Order> findOrdersToProcessOld(){
		if (log.isDebugEnabled()) {
			log.debug("Demande toutes les commandes ");
		}
		
		OrdersResponseToProcessReaderSax reader = new OrdersResponseToProcessReaderSax();
		try {
			System.out.println(reader.getResponse());
		} catch (Exception e) {
			log.error("Echec du reader de l'authentification de l'utilisateur", e);
			//e.printStackTrace(); // a remplacer par un log
		}
		
		List<Order> orders = new ArrayList<>();
		
		
		//Commande 1 
		Customer cust1= new Customer();
		cust1.setUsername("roger@gmail.com");
		cust1.setName("Roger Rabbit");
		
		Order o1 = new Order();
		o1.setId(0);
		o1.setStatus(OrderStatus.Pending);
		o1.setOrderNumber("CDE19-0001");
		o1.setTotalAmount(45.5);
		o1.setDateTime(LocalDateTime.now().minusDays(1));
		o1.setCustomer(cust1);
		
		Product product1 = new Product();
		product1.setId(23);
		product1.setName("Tintin au tibet");
		
		Product product2 = new Product();
		product2.setId(56);
		product2.setName("Tintin en Corse");
		
		OrderLine line1 = new OrderLine();
		line1.setQuantity(1245);
		line1.setQuantity(1);
		line1.setProduct(product1);
		
		OrderLine line2 = new OrderLine();
		line2.setQuantity(1245);
		line2.setQuantity(1);
		line2.setProduct(product2);
		
		o1.addLine(line1);
		o1.addLine(line2);

		//Commande 12
		Customer cust2 = new Customer();
		cust2.setUsername("emile@gmail.com");
		cust2.setName("Emile Lampion");
		
		Order o2 = new Order();
		o2.setId(1);
		o2.setStatus(OrderStatus.Pending);
		o2.setOrderNumber("CDE19-0008");
		o2.setTotalAmount(13);
		o2.setDateTime(LocalDateTime.now().minusDays(1));
		o2.setCustomer(cust2);
		
		Product product3 = new Product();
		product3.setId(45);
		product3.setName("Le chien qui louche");
		
		OrderLine line3 = new OrderLine();
		line3.setQuantity(1245);
		line3.setQuantity(1);
		line3.setProduct(product3);

		o2.addLine(line3);
		
		orders.add(o1);
		orders.add(o2);
		
//		Order o2 = new Order();
//		o2.setId(1);
//		o2.setStatus(OrderStatus.Processing);
//		o2.setOrderNumber("CDE19-0002");
//		o2.setTotalAmount(12.5);
//		o2.setDateTime(LocalDateTime.now());
//		o2.setCustomer(cust1);
//		
//		Order o3 = new Order();
//		o3.setId(2);
//		o3.setStatus(OrderStatus.Processing);
//		o3.setOrderNumber("CDE19-0003");
//		o3.setTotalAmount(22.7);
//		o3.setDateTime(LocalDateTime.now());
//		o3.setCustomer(cust1);
//		
//		orders.add(o1);
//		orders.add(o2);
//		orders.add(o3);
		
		return orders;
	}


}
