package com.infotel.eshop.web;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

//@WebFilter("/profile.jsp")
@WebFilter("/*")
public class SecurityFilter implements Filter {

	private final static Logger log = LogManager.getLogger(SecurityFilter.class);
	
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		
		HttpServletRequest req = (HttpServletRequest)request;
		
		if (log.isDebugEnabled()) {
			log.debug("URI profil : " + req.getRequestURI());
		}
		
		if (req.getRequestURI().contains("profile.jsp") 
				&& req.getSession().getAttribute("customer") == null) {
			RequestDispatcher rd = req.getRequestDispatcher("/login.jsp");
			rd.forward(request, response);
		}
		else if (req.getRequestURI().contains("order") 
				&& req.getSession().getAttribute("customer") == null) {
			RequestDispatcher rd = req.getRequestDispatcher("/login.jsp");
			rd.forward(request, response);
		} 
		else {
			chain.doFilter(request, response);
		}
		
//		HttpSession session = req.getSession();
//		Customer cust = (Customer)session.getAttribute("customer");
//		
//		if (cust == null) {
//			RequestDispatcher rd = req.getRequestDispatcher("/login.jsp");
//			rd.forward(request, response);
//		}
//		else {
//			chain.doFilter(request, response);
//		}
	}

}
