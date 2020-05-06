package com.infotel.eshop.mvc;

import javax.validation.Valid;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.infotel.eshop.exception.AuthenticationException;
import com.infotel.eshop.exception.EShopException;
import com.infotel.eshop.model.Customer;
import com.infotel.eshop.mvc.form.LoginForm;
import com.infotel.eshop.service.UserService;

@Controller @RequestMapping("/login")
@SessionAttributes("customer")
public class LoginController {

	private final static Logger log = LogManager.getLogger(LoginController.class);
	
	@Autowired
	private UserService service;
	
	@ModelAttribute//("loginForm") //invoque setup puis showForm
	public LoginForm setup() {
		return new LoginForm();
	}
	
	@GetMapping // prepare la page avec le bean
	public String showForm(Model model) {
		//model.addAttribute("loginForm", new LoginForm()); // plus besoin car on a créé methode setup
		return "login";
	}
	
	@PostMapping // se connecter declenche login
	public String authenticate(@ModelAttribute/*("loginForm")*/ @Valid LoginForm form, 
			BindingResult result, Model model) {
		
		if (result.hasErrors()) { // on court circuite pr ne pas invoquer le service métier
			return "login";
		}
		
		if (log.isDebugEnabled()) {
			log.debug("Demande authentification : " + form.getUsername() + "/" + form.getPassword());
		}

		try {
			Customer cust = service.authenticateCustomer(form.getUsername(), form.getPassword());
			model.addAttribute("customer", cust);
			return "redirect:home";
		} catch (AuthenticationException | EShopException e) {
			model.addAttribute("authFailed", "true");
			return "login";
		}

	}
}
