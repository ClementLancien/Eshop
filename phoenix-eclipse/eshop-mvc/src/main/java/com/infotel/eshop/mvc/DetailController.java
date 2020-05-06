package com.infotel.eshop.mvc;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.infotel.eshop.model.OrderLine;
import com.infotel.eshop.model.Product;
import com.infotel.eshop.mvc.form.AddToBasketForm;
import com.infotel.eshop.service.CatalogService;

@Controller @RequestMapping("/detail")
@SessionAttributes("basket")
public class DetailController {

	private final static Logger log = LogManager.getLogger(DetailController.class);
	
	@Autowired
	private CatalogService service;
	
	@ModelAttribute
	public Product setupProduct(@RequestParam("id") int id) throws Exception {
		return service.findProductById(id);
	}
	
	@ModelAttribute
	public AddToBasketForm addToBasketForm() throws Exception {
		return new AddToBasketForm();
	}
	
	@GetMapping
	public String showDetail() {
		return "detail";
	}
	
	@PostMapping
	public String addToBasket(@ModelAttribute AddToBasketForm form,
							  Model model) throws Exception {
		
		if (log.isDebugEnabled()) {
			log.debug("post-----------------------------------------------------");
		}
		
		List<OrderLine> basket = new ArrayList<>();
		
		if (!model.containsAttribute("basket")) {
			//basket = new ArrayList<>();
			model.addAttribute("basket", basket);
		}
		basket = (List<OrderLine>)model.getAttribute("basket");
		
		Product product = service.findProductById(form.getId());
		
		boolean isNotFound = false;
		for (OrderLine l : basket) {
			if(l.getProduct().equals(product)) {
				l.setQuantity(l.getQuantity() + form.getQuantity());
				isNotFound = true;
				break;
			}
		}
		
		if(!isNotFound) {
			OrderLine line = new OrderLine();
			line.setId(basket.size()+1);
			line.setProduct(product);
			line.setQuantity(form.getQuantity()); 
			basket.add(line);
		}
		model.addAttribute("basket", basket);
		
		if (log.isDebugEnabled()) {
			log.debug("Size basket : " + basket.size());
		}
		model.addAttribute("addToBasketSuccess", "true");
		
		return "detail";
	}
}