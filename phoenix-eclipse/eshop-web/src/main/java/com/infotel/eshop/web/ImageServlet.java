package com.infotel.eshop.web;

import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.infotel.eshop.model.ProductImage;
import com.infotel.eshop.service.CatalogService;


@WebServlet("/images")
public class ImageServlet extends HttpServlet {

	//private CatalogService service = new CatalogServiceImpl();
	private static ApplicationContext ctx;
	
	@Override
	public void init() throws ServletException {
		ctx = WebApplicationContextUtils.getWebApplicationContext(getServletContext());
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int id = Integer.parseInt(req.getParameter("id"));
		
//		ApplicationContext ctx = 
//				WebApplicationContextUtils.getWebApplicationContext(getServletContext());
		CatalogService service = ctx.getBean(CatalogService.class);
		ProductImage image = service.findProductImageById(id);
		
		resp.setContentType("/image/png"); // header -> contentType
		
		OutputStream os = resp.getOutputStream();
		os.write(image.getContent());
		os.flush(); // pr etre sur que tt est pousser dans le flux
	}
	
}
