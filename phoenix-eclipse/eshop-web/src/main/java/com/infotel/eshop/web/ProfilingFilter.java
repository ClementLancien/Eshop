package com.infotel.eshop.web;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@WebFilter("/*")
public class ProfilingFilter implements Filter {

	//private final static Logger log = LogManager.getLogger(ProfilingFilter.class);
	
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		
//		HttpServletRequest req = (HttpServletRequest)request;
//		
//		Instant before = Instant.now();
		chain.doFilter(request, response);
//		Instant after = Instant.now();
//		
//		if (log.isDebugEnabled()) {
//			log.debug("Temps de chargement : " + req.getRequestURI() + " => " + Duration.between(before, after));
//		}
	}

}
