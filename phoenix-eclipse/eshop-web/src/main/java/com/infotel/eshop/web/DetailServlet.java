package com.infotel.eshop.web;

import java.io.IOException;
import java.time.format.DateTimeFormatter;
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
import com.infotel.eshop.model.Album;
import com.infotel.eshop.model.Book;
import com.infotel.eshop.model.Movie;
import com.infotel.eshop.model.OrderLine;
import com.infotel.eshop.model.Product;
import com.infotel.eshop.service.CatalogService;

public class DetailServlet extends HttpServlet {

	private final static Logger log = LogManager.getLogger(DetailServlet.class);
	
	private static ApplicationContext ctx;
	
	@Override
	public void init() throws ServletException {
		ctx = WebApplicationContextUtils.getWebApplicationContext(getServletContext());
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//Product product = (Product)req.getAttribute("product");
		//int productId = Integer.parseInt(req.getParameter("productId"));
		
		int productId = Integer.parseInt(req.getParameter("id"));
		
		//CatalogService service = new CatalogServiceImpl();
//		ApplicationContext ctx = 
//				WebApplicationContextUtils.getWebApplicationContext(getServletContext());
		CatalogService service = ctx.getBean(CatalogService.class);
		//Product product = null;
		try {
			Product product = service.findProductById(productId);
			req.setAttribute("product", product);
			
			if (log.isDebugEnabled()) {
				log.debug("detail product : " + product.getDescription());
			}
			DateTimeFormatter dateFmt = DateTimeFormatter.ofPattern("dd/MM/yyyy");
			if (product instanceof Book) {
				Book book = (Book) product;
				req.setAttribute("date", book.getReleaseDate().format(dateFmt));
			}
			if (product instanceof Album) {
				Album album = (Album) product;
				req.setAttribute("date", album.getReleaseDate().format(dateFmt));
			}
			
			if (product instanceof Movie) {
				Movie movie = (Movie) product;
				req.setAttribute("date", movie.getReleaseDate().format(dateFmt));
			}
			
			//DateTimeFormatter dateFmt = DateTimeFormatter.ofPattern("dd/MM/yyyy");
			//req.setAttribute("date", product.getReleaseDate().format(dateFmt));
			
		} catch (EShopException e) {
			log.error("Echec lecture du detail d'un produit", e);
			throw new ServletException("Echec lecture du produit : " + req.getParameter("id"));
		}
		RequestDispatcher rd = req.getRequestDispatcher("detail.jsp");
		rd.forward(req, resp);
//		req.setAttribute("product", product);
//		
//		DateTimeFormatter dateFmt = DateTimeFormatter.ofPattern("dd/MM/yyyy");
//		req.setAttribute("date", product.getReleaseDate().format(dateFmt));
//		
//		RequestDispatcher rd = req.getRequestDispatcher("/detail.jsp");
//		rd.forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        
		//Enumeration<String> parameterNames = req.getParameterNames();
		int productId = Integer.parseInt(req.getParameter("id"));
		int quantity = Integer.parseInt(req.getParameter("quantity"));
		
		//CatalogService service = new CatalogServiceImpl();
//		ApplicationContext ctx = 
//				WebApplicationContextUtils.getWebApplicationContext(getServletContext());
		CatalogService service = ctx.getBean(CatalogService.class);
		try {
			Product product = service.findProductById(productId);
			req.setAttribute("product", product);
			
//			DateTimeFormatter dateFmt = DateTimeFormatter.ofPattern("dd/MM/yyyy");
//			req.setAttribute("date", product.getReleaseDate().format(dateFmt));
			
			DateTimeFormatter dateFmt = DateTimeFormatter.ofPattern("dd/MM/yyyy");
			if (product instanceof Book) {
				Book book = (Book) product;
				req.setAttribute("date", book.getReleaseDate().format(dateFmt));
				req.setAttribute("category", "book");
			}
			if (product instanceof Album) {
				Album album = (Album) product;
				req.setAttribute("date", album.getReleaseDate().format(dateFmt));
				req.setAttribute("category", "album");
			}
			
			if (product instanceof Movie) {
				Movie movie = (Movie) product;
				req.setAttribute("date", movie.getReleaseDate().format(dateFmt));
				req.setAttribute("category", "movie");
			}
			
			addToBasket(req, quantity, product);
			
			req.setAttribute("addToBasketSuccess", "true");
			
			
		} catch (EShopException e) {
			log.error("Echec ajout d'un produit au panier", e);
//			throw new ServletException("Echec ajout d'un produit au panier : " + req.getParameter("id"));
			req.setAttribute("addToBasketfail", "true");
		}
		
		RequestDispatcher rd = req.getRequestDispatcher("detail.jsp");
		rd.forward(req, resp);
//		int quantity = Integer.parseInt(req.getParameter("quantity"));
//		String prod = (String)req.getParameter("product");
////		int id = req.getParameter("id");
//
//		prod=prod.substring(9, prod.length()-1);
//		List<String> items = Arrays.asList(prod.split("\\s*,\\s*"));
//		
//		Product product = new Product();
//		product.setId(Integer.parseInt(items.get(0).substring(3)));
//		product.setName(items.get(1).substring(5));
//		product.setPrice(Double.parseDouble(items.get(2).substring(6)));
//
//		addToBasket(req, quantity, product);
//		
//		if (log.isDebugEnabled()) {
//			log.debug("quantité selectionné : " + quantity);
//			log.debug("produit selectionné : " + product);
//			HttpSession session = req.getSession();
//			log.debug("basket selectionné : " + session.getAttribute("basket"));
//			log.debug("IDentifiant : " + req.getParameter("id"));
//		}
//		
//		req.setAttribute("product", product);
//		req.setAttribute("date", date);
//		req.setAttribute("id", req.getParameter("id"));
//		
//		RequestDispatcher rd = req.getRequestDispatcher("/detail.jsp");
//		rd.forward(req, resp);

	}

	private void addToBasket(HttpServletRequest req, int quantity, Product product) {
		HttpSession session = req.getSession();
		List<OrderLine> basket;
		if (session.getAttribute("basket") == null) {
			basket = new ArrayList<>();
			session.setAttribute("basket", basket);
		}
		
		basket = (List<OrderLine>)session.getAttribute("basket");
		boolean isNotFound = false;
		for (OrderLine l : basket) {
			if(l.getProduct().equals(product)) {
				l.setQuantity(l.getQuantity() + quantity);
				isNotFound = true;
				break;
			}
		}
		
		if(!isNotFound) {
			OrderLine line = new OrderLine();
			line.setId(basket.size()+1);
			line.setProduct(product);
			line.setQuantity(quantity); 
			basket.add(line);
		}
		session.setAttribute("basket", basket);
	}
}
