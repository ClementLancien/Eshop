package com.infotel.eshop.fx.service.xml;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import com.infotel.eshop.fx.model.Customer;
import com.infotel.eshop.fx.model.Order;
import com.infotel.eshop.fx.model.OrderLine;
import com.infotel.eshop.fx.model.Product;

public class OrdersResponseToProcessHandler extends DefaultHandler {
	private List<Order> orders;
	
	private String currentElt;
	
	public List<Order> getOrders() {
		return orders;
	}

	private Order getLastOrder() {
		return orders.get(orders.size() - 1);
	}
	public OrderLine getLastOrderLine() {
		return getLastOrder().getLines().get(getLastOrder().getLines().size() -1);
	}
	
	@Override
	public void startDocument() throws SAXException {
		//System.out.println("START DOC");
		orders = new ArrayList<>();
	}
	
	@Override
	public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
		//System.out.println("START ELE : " + qName);
		currentElt = qName;
		
		switch (qName) {
		case "Order":
			Order order = new Order();
			order.setId(Integer.parseInt(attributes.getValue("id")));
			order.setOrderNumber(attributes.getValue("number"));
			orders.add(order);
			break;
			
		case "Customer":
			getLastOrder().setCustomer(new Customer());
			break;
			
		case "OrderLine":
			OrderLine line = new OrderLine();
			line.setId(Integer.parseInt(attributes.getValue("id")));
			getLastOrder().addLine(line);
			break;
			
		case "Product":
			Product p = new Product();
			p.setId(Integer.parseInt(attributes.getValue("id")));
			p.setName(attributes.getValue("name"));
			getLastOrderLine().setProduct(p);
			break;
		}
	
	}
	
	@Override
	public void characters(char[] ch, int start, int length) throws SAXException {
		String str = new String(ch, start, length);
		
		if (currentElt == null) return;
		switch (currentElt) {
					
		case "Date":
			LocalDateTime date = LocalDateTime.parse(str);
			getLastOrder().setDateTime(date);
			currentElt = null;
			break;

		case "FirstName":
			getLastOrder().getCustomer().setName(str);
			currentElt = null;
			break;
			
		case "LastName":
			str = getLastOrder().getCustomer().getName() + " " + str;
			getLastOrder().getCustomer().setName(str);
			currentElt = null;
			break;
			
		case "Quantity":
			getLastOrderLine().setQuantity(Integer.parseInt(str));
			currentElt = null;
			break;
		}
	}

	@Override
	public void endElement(String uri, String localName, String qName) throws SAXException {
		//System.out.println("END ELE : " + qName);
	}
	
	@Override
	public void endDocument() throws SAXException {
	}
	
}
