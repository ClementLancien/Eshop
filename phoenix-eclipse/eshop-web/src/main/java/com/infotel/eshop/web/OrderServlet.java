package com.infotel.eshop.web;

import java.io.IOException;
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
import com.infotel.eshop.service.OrderService;

public class OrderServlet extends HttpServlet {

	private final static Logger log = LogManager.getFormatterLogger(OrderServlet.class);
	private static ApplicationContext ctx;
	
	@Override
	public void init() throws ServletException {
		ctx = WebApplicationContextUtils.getWebApplicationContext(getServletContext());
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		if (log.isDebugEnabled()) {
			log.debug("MyOrderServle doGet");
		}
		
		//OrderService service = new OrderServiceImpl();
//		ApplicationContext ctx = 
//				WebApplicationContextUtils.getWebApplicationContext(getServletContext());
		OrderService service = ctx.getBean(OrderService.class);
		
		HttpSession session = req.getSession();
		
		Customer cust = (Customer)session.getAttribute("customer");
		//List<Order> orders = null;
		try {
			List<Order> orders = service.findOrdersByCustomer(cust.getUsername());
			
			session.setAttribute("orders", orders);
			if (log.isDebugEnabled()) {
				log.debug("MyOrders size : " + orders.size());
			}
			
		} catch (EShopException e) {
			log.error("Probleme commandes passées");
		}
		
		RequestDispatcher rd = req.getRequestDispatcher("order.jsp");
		rd.forward(req, resp);

	}
}
