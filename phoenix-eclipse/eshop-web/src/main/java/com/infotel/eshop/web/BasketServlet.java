package com.infotel.eshop.web;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.infotel.eshop.exception.EShopException;
import com.infotel.eshop.model.Customer;
import com.infotel.eshop.model.Order;
import com.infotel.eshop.model.OrderLine;
import com.infotel.eshop.service.OrderService;

public class BasketServlet extends HttpServlet {

	private final static Logger log = LogManager.getLogger(BasketServlet.class);
	private static ApplicationContext ctx;
	
	@Override
	public void init() throws ServletException {
		ctx = WebApplicationContextUtils.getWebApplicationContext(getServletContext());
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		HttpSession session = req.getSession();
		
		Order order = new Order();
		Customer cust = (Customer)session.getAttribute("customer");
		
		if (cust == null) {
			RequestDispatcher rd = req.getRequestDispatcher("/login.jsp");
			rd.forward(req, resp);
		} else {
			order.setCustomer(cust);
			for (OrderLine line : (List<OrderLine>)session.getAttribute("basket")) {
				order.addLine(line.getProduct(), line.getQuantity());
			}
			//order.setLines((List<OrderLine>)session.getAttribute("basket"));
			
			//OrderService service = new OrderServiceImpl();
//			ApplicationContext ctx = 
//					WebApplicationContextUtils.getWebApplicationContext(getServletContext());
			OrderService service = ctx.getBean(OrderService.class);
			try {
				service.placeOrder(order);
			} catch (EShopException e) {
				log.error("Erreur d'ajout de la commande dans la base", e);
			}
			
			session.setAttribute("basket", new ArrayList<>());
			
			req.setAttribute("orderCommandSuccess", "true");
			
			RequestDispatcher rd = req.getRequestDispatcher("/basket.jsp");
			rd.forward(req, resp);
		}
	}
}
