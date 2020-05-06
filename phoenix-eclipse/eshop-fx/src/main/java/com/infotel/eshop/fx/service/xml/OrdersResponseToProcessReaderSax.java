package com.infotel.eshop.fx.service.xml;

import java.io.File;
import java.util.List;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import com.infotel.eshop.fx.model.Order;

public class OrdersResponseToProcessReaderSax {
	
	public List<Order> getResponse() throws Exception {
		
		SAXParserFactory factory = SAXParserFactory.newInstance();
		SAXParser parser = factory.newSAXParser();
		
		OrdersResponseToProcessHandler1 handler = new OrdersResponseToProcessHandler1();
		parser.parse(new File("xml\\OrdersToProcessResponse.xml"), handler);
		return handler.getOrders();
	}

}
