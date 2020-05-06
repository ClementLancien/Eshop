package com.infotel.eshop.web;

import java.io.IOException;
import java.util.List;

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
import com.infotel.eshop.model.Product;
import com.infotel.eshop.model.ProductFamily;
import com.infotel.eshop.service.CatalogService;

//@WebServlet(urlPatterns = "/search", loadOnStartup = 1)
public class SearchServlet extends HttpServlet {
	
	private final static Logger log = LogManager.getLogger(SearchServlet.class);
	private static ApplicationContext ctx;
	
	@Override
	public void init() throws ServletException {
		ctx = WebApplicationContextUtils.getWebApplicationContext(getServletContext());
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//		resp.setContentType("text/html"); // Quand on ecrit dans le body
//		resp.setCharacterEncoding("UTF-8");
		
		//CatalogService service = new CatalogServiceImpl();
//		ApplicationContext ctx = 
//				WebApplicationContextUtils.getWebApplicationContext(getServletContext());
		CatalogService service = ctx.getBean(CatalogService.class);
		List<ProductFamily> families;
		try {
			families = service.findAllFamilies();
			req.setAttribute("families", families);
		} catch (EShopException e) {
			log.error("Echec lecture des familles de produits", e);
		}
		
		if (req.getParameter("keyword") != null) {
			String keyword = req.getParameter("keyword");
			int familyId = -1;
			if (req.getParameter("familyId") != null) {
				familyId = Integer.parseInt(req.getParameter("familyId"));
			}
			
			List<Product> products = null;
			try {
				if (keyword != null) {
					//products = service.findProductByCriteria(keyword, familyId, -1, false);
					req.setAttribute("products", products);
				}
				//RequestDispatcher rd = req.getRequestDispatcher("search.jsp");
				//rd.forward(req, resp);
				
			} catch (Exception e) {
				log.error("Echec lecture des produits", e);
				//throw new ServletException("Echec lecture des produits", e);
			}
			
		}
		RequestDispatcher rd = req.getRequestDispatcher("search.jsp");
		rd.forward(req, resp);
		
//		String keyword = req.getParameter("keyword");
//		int familyId = -1;
//		if (req.getParameter("familyId") != null) {
//			familyId = Integer.parseInt(req.getParameter("familyId"));
//		}
//		
//		List<Product> products = null;
//		try {
//			if (keyword != null) {
//				products = service.findProductByCriteria(keyword, familyId);
//				req.setAttribute("products", products);
//			}
//			
//			RequestDispatcher rd = req.getRequestDispatcher("search.jsp");
//			rd.forward(req, resp);
//			
//		} catch (EShopException e) {
//			throw new ServletException("Echec lecture des produits", e);
//		}
		
//		PrintWriter pw = resp.getWriter();
//		pw.println("<!DOCTYPE html>");
//		pw.println("<html lang=\"en\">");
//		pw.println("<head>");
//		pw.println("<meta charset=\"UTF-8\">");
//		pw.println("<meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">");
//		pw.println("<title>Recherche</title>");
//		pw.println("</head>");
//		pw.println("<body>");
//		
//		pw.println("<form action=\"search\" method=\"GET\">");
//		pw.println("<input type=\"text\" name=\"keyword\">");
//		
//		pw.println("<select name=\"familyId\">");
//		pw.println("<option value=\"-1\">-- Pas de famille --</option>");
//		for (ProductFamily family : families) {
//			pw.println("<option value=\"" + family.getId() + "\">" + family.getName() +"</option>");
//		}
//		pw.println("</select>");
//		
//		pw.println("<input type=\"submit\" value=\"Chercher!\">");
//		pw.println("</form>");
//		
//		if (products != null) {
//			pw.println("<ul>");
//			for (Product product : products) {
//				pw.println("<li>" + product.getName() + "</li>");
//			}
//			pw.println("</ul>");
//		}
//		
//		pw.println("</body>");
//		pw.println("</html>");
	}
	
}
