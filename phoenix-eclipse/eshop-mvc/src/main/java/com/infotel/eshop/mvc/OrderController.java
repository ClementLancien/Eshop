package com.infotel.eshop.mvc;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.infotel.eshop.model.Customer;
import com.infotel.eshop.model.Order;
import com.infotel.eshop.service.OrderService;

@Controller
@RequestMapping("/order")
@SessionAttributes("customer")
public class OrderController {
	
	private final static Logger log = LogManager.getLogger(OrderController.class);
	
	@Autowired
	private OrderService service;
	
	@GetMapping
	public String showOrders(@ModelAttribute("customer") Customer cust, Model model) throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("nom client : " + cust.getUsername());
		}
		List<Order> orders = service.findOrdersByCustomer(cust.getUsername());
		if (log.isDebugEnabled()) {
			log.debug("taille commandes pass " + orders.size());
		}
		model.addAttribute("orders", orders);
		return "order";
	}
}
