package com.infotel.eshop.ws;

import java.util.List;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import com.infotel.eshop.model.Address;
import com.infotel.eshop.model.Customer;
import com.infotel.eshop.model.Order;
import com.infotel.eshop.model.OrderLine;
import com.infotel.eshop.service.OrderService;

@Endpoint
public class OrderToProcessEndpoint {

	private final OrderService service;

	public OrderToProcessEndpoint(OrderService service) {
		super();
		this.service = service;
	}
	
	@PayloadRoot(localPart="OrdersToProcessRequest", namespace="http://www.infotel.com/eshop")
	@ResponsePayload
	public Element getOrdersToProcess() { // @RequestPayload Element request // pas utile car le body de la requete est vide
		
		List<Order> orders = service.findOrdersToProcess();
		
		Document docResp = DocumentHelper.createDocument();
		Element rootElt = docResp.addElement("OrdersToProcessResponse", "http://www.infotel.com/eshop");
		//DateTimeFormatter dateFmt = DateTimeFormatter.ISO_DATE_TIME;
		for (Order order : orders) {
			Element orderElt = rootElt.addElement("Order");
			orderElt.addAttribute("id", Integer.toString(order.getId()));
			orderElt.addAttribute("number", order.getOrderNumber());
			orderElt.addElement("Status").setText(order.getStatus().name());
			orderElt.addElement("Date").setText(order.getDateTime().toString());
			//orderElt.addElement("Date").setText(order.getDateTime().format(dateFmt));
			
			Customer cust = order.getCustomer();
			Element custElt = orderElt.addElement("Customer");
			custElt.addElement("FirstName").addText(cust.getFirstName());
			custElt.addElement("LastName").addText(cust.getLastName());
			
			Address addr = cust.getAddress();
			Element addrElt = custElt.addElement("Address");
			addrElt.addElement("Street").setText(addr.getStreet());
			addrElt.addElement("PostCode").setText(addr.getPostcode());
			addrElt.addElement("City").setText(addr.getCity());
			//addrElt.addElement("Country").setText(addr.getCountry());
			
			Element linesElt = orderElt.addElement("OrderLines");
			for (OrderLine line : order.getLines()) {
				Element lineElt = linesElt.addElement("OrderLine");
				lineElt.addAttribute("id", Integer.toString(line.getId()));
				
				Element prodElt = lineElt.addElement("Product");
				prodElt.addAttribute("id", Integer.toString(line.getProduct().getId()));
				prodElt.addAttribute("name", line.getProduct().getName());
				
				lineElt.addElement("Quantity").setText(Integer.toString(line.getQuantity()));
			}
		}
		return rootElt;
	}
}
