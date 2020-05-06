package com.infotel.eshop.mvc;

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
import org.springframework.stereotype.Component;

@Component
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
		
		if (req.getRequestURI().contains("profile") 
				&& req.getSession().getAttribute("customer") == null) {
			RequestDispatcher rd = req.getRequestDispatcher("login");
			rd.forward(request, response);
		}
		else if (req.getRequestURI().contains("order") 
				&& req.getSession().getAttribute("customer") == null) {
			RequestDispatcher rd = req.getRequestDispatcher("login");
			rd.forward(request, response);
		} 
		else {
			chain.doFilter(request, response);
		}
	}

}
