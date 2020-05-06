package com.infotel.eshop.mvc;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {

//	@RequestMapping("/home")
//	public ModelAndView showHome() {
//		Map<String, Object> map = new HashMap<>();
//		
//		Customer cust= new Customer();
//		cust.setFirstName("Rogger");
//		cust.setLastName("Rabbit");
//		
//		map.put("customer", cust);
//		return new ModelAndView("home", map);
//	} // map comme le req.setAttribute
	
	
	
//	// si on a qu'un seul parametre
//	@RequestMapping("/home")
//	public ModelAndView showHome() {
//		//Map<String, Object> map = new HashMap<>();
//		
//		Customer cust= new Customer();
//		cust.setFirstName("Roger");
//		cust.setLastName("Rabbit");
//		//map.put("customer", cust);
//
//		return new ModelAndView("home", "customer", cust); // on peut mettre autant de donnees qu'on veut car param.. object
//	}
	
	// si on a qu'un seul parametre
//	@RequestMapping("/home")
//	public ModelAndView showHome(@RequestParam("name") String thename) { // lecture parametre http
//		//Map<String, Object> map = new HashMap<>();
//		
//		Customer cust= new Customer();
//		cust.setFirstName(thename);
//		cust.setLastName("Rabbit");
//		//map.put("customer", cust);
//
//		return new ModelAndView("home", "customer", cust); // on peut mettre autant de donnees qu'on veut car param.. object
//	}
	
//	@RequestMapping("/home")
//	public String showHome() {
//		return "home";
//	}
	
//	@RequestMapping("/home")
//	public String showHome(@RequestParam("name") String thename, Model model) {
//		Customer cust= new Customer();
//		cust.setFirstName(thename);
//		cust.setLastName("Rabbit");
//		model.addAttribute("customer", cust);
//		
//		return "home";
//	}
	
	//@RequestMapping("/home/{name}")
	@RequestMapping("/home")
	//public String showHome(@PathVariable("name") String thename, Model model) {
	public String showHome(Model model) {
//		Customer cust= new Customer();
//		cust.setFirstName("roger");
//		cust.setLastName("Rabbit");
//		model.addAttribute("customer", cust);
		
		return "home";
	}
	
}
