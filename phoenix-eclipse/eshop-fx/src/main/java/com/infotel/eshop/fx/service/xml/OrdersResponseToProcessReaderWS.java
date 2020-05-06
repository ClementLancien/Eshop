package com.infotel.eshop.fx.service.xml;

import java.io.File;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.xml.transform.Source;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.DocumentResult;
import org.dom4j.io.DocumentSource;
import org.dom4j.io.SAXReader;
import org.springframework.ws.client.core.WebServiceTemplate;

import com.infotel.eshop.fx.model.Address;
import com.infotel.eshop.fx.model.Customer;
import com.infotel.eshop.fx.model.Order;
import com.infotel.eshop.fx.model.OrderLine;
import com.infotel.eshop.fx.model.OrderStatus;
import com.infotel.eshop.fx.model.Product;

public class OrdersResponseToProcessReaderWS {

	public List<Order> getResponse() throws Exception {
		
		Document docReq = DocumentHelper.createDocument();
		docReq.addElement("OrdersToProcessRequest", "http://www.infotel.com/eshop");
		
		Source source = new DocumentSource(docReq);
		DocumentResult result = new DocumentResult();
		
		WebServiceTemplate client = new WebServiceTemplate();
		client.setDefaultUri("http://sem148.sesame.infotel.com:8080/eshop-ws/services");
		client.sendSourceAndReceiveToResult(source, result);
		
		Document docResp = result.getDocument();
		Element respElt = docResp.getRootElement();
		
		List<Order> orders = new ArrayList<>();
		respElt.elements().stream()
					      .forEach(element -> {
					    	  Order order = new Order();
					    	  order.setId(Integer.parseInt(element.attributeValue("id")));
					    	  order.setOrderNumber(element.attributeValue("number"));
					    	  order.setStatus(OrderStatus.valueOf(element.elementText("Status")));
					    	  order.setDateTime(LocalDateTime.parse(element.elementText("Date")));
					    	  
					    	  Customer cust = new Customer();
					    	  cust.setUsername(element.element("Customer").elementText("FirstName"));
							  cust.setName(element.element("Customer").elementText("FirstName") + " " + element.element("Customer").elementText("LastName"));
							 
							  Address addr = new Address();
							  addr.setStreet(element.element("Customer").element("Address").elementText("Street"));
							  addr.setPostCode(element.element("Customer").element("Address").elementText("PostCode"));
							  addr.setCity(element.element("Customer").element("Address").elementText("City"));
							  cust.setAddress(addr);
							  
							  order.setCustomer(cust);
							  
							  List<Element> orderLines = element.element("OrderLines").elements();
							  orderLines.stream()
							  		    .forEach(orderLine -> {
								  			OrderLine line = new OrderLine();
											Product product = new Product();
											
											line.setId(Integer.parseInt(orderLine.attributeValue("id")));
											
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
