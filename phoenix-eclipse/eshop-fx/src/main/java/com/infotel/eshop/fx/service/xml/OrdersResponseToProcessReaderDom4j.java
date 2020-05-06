package com.infotel.eshop.fx.service.xml;

import java.io.File;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import com.infotel.eshop.fx.model.Customer;
import com.infotel.eshop.fx.model.Order;
import com.infotel.eshop.fx.model.OrderLine;
import com.infotel.eshop.fx.model.Product;

public class OrdersResponseToProcessReaderDom4j {

	public List<Order> getResponse() throws Exception {
		List<Order> orders = new ArrayList<>();
		
		SAXReader reader = new SAXReader();
		Document doc = reader.read(new File("xml\\OrdersToProcessResponse.xml"));
		
		Element rootElt = doc.getRootElement();
		
		
		rootElt.elements().stream()
					      .forEach(element -> {
					    	  Order order = new Order();
					    	  order.setId(Integer.parseInt(element.attributeValue("id")));
					    	  order.setOrderNumber(element.attributeValue("number"));
					    	  order.setDateTime(LocalDateTime.parse(element.elementText("Date")));
				
					    	  Customer cust = new Customer();
					    	  cust.setUsername(element.element("Customer").elementText("FirstName"));
							  cust.setName(element.element("Customer").elementText("FirstName") + " " + element.element("Customer").elementText("LastName"));
					    	  
							  order.setCustomer(cust);
							  
							  List<Element> orderLines = element.element("OrderLines").elements();
							  orderLines.stream()
							  		    .forEach(orderLine -> {
								  			OrderLine line = new OrderLine();
											Product product = new Product();
											
											product.setId(Integer.parseInt((orderLine.element("Product").attributeValue("id"))));
											product.setName((orderLine.element("Product").attributeValue("name")));
											
											line.setProduct(product);
											line.setQuantity(Integer.parseInt(orderLine.elementText("Quantity")));
											
											order.addLine(line);
							  		    });
							  orders.add(order);
					      });
		return orders;
	}
		
		public List<Order> getResponse2() throws Exception {
			
		List<Order> orders = new ArrayList<>();
		
		SAXReader reader = new SAXReader();
		Document doc = reader.read(new File("xml\\OrdersToProcessResponse.xml"));
		
		Element rootElt = doc.getRootElement();	
		List<Element> elements = rootElt.elements();
		for (int i = 0; i < elements.size(); i++) {
			Order order = new Order();
			order.setId(Integer.parseInt(elements.get(i).attributeValue("id")));
			order.setOrderNumber(elements.get(i).attributeValue("number"));
			order.setDateTime(LocalDateTime.parse(elements.get(i).elementText("Date")));
			
			Customer cust = new Customer();
			cust.setUsername(elements.get(i).element("Customer").elementText("FirstName"));
			cust.setName(elements.get(i).element("Customer").elementText("FirstName") + " " + elements.get(i).element("Customer").elementText("LastName"));
			
			order.setCustomer(cust);
			
			List<Element> orderLines =  elements.get(i).element("OrderLines").elements();
			for (int j = 0; j < orderLines.size(); j++) {
				OrderLine line = new OrderLine();
				Product product = new Product();
				
				product.setId(Integer.parseInt((orderLines.get(j).element("Product").attributeValue("id"))));
				product.setName((orderLines.get(j).element("Product").attributeValue("name")));
				
				line.setProduct(product);
				line.setQuantity(1);
				line.setQuantity(Integer.parseInt(orderLines.get(j).elementText("Quantity")));
				
				order.addLine(line);
			}
			orders.add(order);
		}
		return orders;
	}
}
