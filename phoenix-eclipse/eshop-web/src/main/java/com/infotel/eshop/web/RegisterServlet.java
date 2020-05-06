package com.infotel.eshop.web;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.infotel.eshop.exception.EShopException;
import com.infotel.eshop.exception.RegisterException;
import com.infotel.eshop.model.Customer;
import com.infotel.eshop.service.UserService;

public class RegisterServlet extends HttpServlet {
	
	private final static Logger log = LogManager.getLogger(RegisterServlet.class);

	private static ApplicationContext ctx;
	
	@Override
	public void init() throws ServletException {
		ctx = WebApplicationContextUtils.getWebApplicationContext(getServletContext());
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String firstName = req.getParameter("firstname");
		String lastName = req.getParameter("lastname");
		String username = req.getParameter("username");
		String password = req.getParameter("password");
		
		if (log.isDebugEnabled()) {
			log.debug("Register firstname : " + firstName);
			log.debug("Register lastname : " + lastName);
			log.debug("Register username : " + username);
			log.debug("Register password : " + password);
		}
		
		//UserService service = new UserServiceImpl();
//		ApplicationContext ctx = 
//				WebApplicationContextUtils.getWebApplicationContext(getServletContext());
		UserService service = ctx.getBean(UserService.class);
		
		try {
			Customer cust = new Customer(username, password, firstName, lastName);
			service.registerCustomer(cust);
			req.setAttribute("registerSuccess", "true");
		} catch (EShopException | RegisterException e) {
			req.setAttribute("registerFail", "true");
			log.error("Echec de l'enregistrement", e);
		}
		
		RequestDispatcher rd = req.getRequestDispatcher("register.jsp");
		rd.forward(req, resp);
	}

}
