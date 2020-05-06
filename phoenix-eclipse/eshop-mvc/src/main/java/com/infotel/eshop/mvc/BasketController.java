package com.infotel.eshop.mvc;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.infotel.eshop.exception.EShopException;
import com.infotel.eshop.model.Customer;
import com.infotel.eshop.model.Order;
import com.infotel.eshop.model.OrderLine;
import com.infotel.eshop.service.OrderService;

@Component @RequestMapping("/basket")
@SessionAttributes(names = {
		"customer",
		"basket"
})
public class BasketController {
	private final static Logger log = LogManager.getLogger(BasketController.class);
	
	@Autowired
	private OrderService service;
	
	@GetMapping
	public String showBasket() {
		return "basket";
	}
	
	@PostMapping
	public String placeOrder(Model model) {
		Customer cust = (Customer) model.getAttribute("customer");
		if(log.isDebugEnabled()) {
			log.debug("custom : " + cust);
		}
		
		if(cust == null) {
			return "redirect:login";
		} else {
			Order order = new Order();
			order.setCustomer(cust);
			for (OrderLine line : (List<OrderLine>)model.getAttribute("basket")) {
				order.addLine(line.getProduct(), line.getQuantity());
			}
			try {
				service.placeOrder(order);
			} catch (EShopException e) {
				log.error("Erreur d'ajout de la commande dans la base", e);
			}
			model.addAttribute("basket", new ArrayList<>());
			model.addAttribute("orderCommandSuccess", "true");
			return "basket";
		}
	}
	
}
