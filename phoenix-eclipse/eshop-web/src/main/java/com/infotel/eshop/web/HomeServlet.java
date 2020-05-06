package com.infotel.eshop.web;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.infotel.eshop.model.Customer;

public class HomeServlet extends HttpServlet {

	public String app;
	
	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		System.out.println("HelloServlet démarre");
		
		//app = config.getInitParameter("appName");
		app = getServletContext().getInitParameter("appName2");
		
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		HttpSession session = req.getSession();
		Customer cust = (Customer) session.getAttribute("customer");
		if (cust != null) {
			req.setAttribute("customer", cust);
			//pw.println("<h1>Bienvenue " + cust.getFirstName() + " " + cust.getLastName() + " !</h1>");
		}
		
		RequestDispatcher rd = req.getRequestDispatcher("/home.jsp");
		rd.forward(req, resp);
	}
	
//	@Override
//	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//		
////		Cookie[] cookies = req.getCookies();
////		String fruit  = null;
////		if (cookies != null) {
////			for (Cookie cookie : cookies) {
////				System.out.println(cookie.getName() + " => " + cookie.getValue());
////				if (cookie.getName().equals("fruit")) {
////					fruit = cookie.getValue();
////				}
////			}
////		}
////		
////		if (fruit == null) {
////			Cookie cookie = new Cookie("fruit", "pomme");
////			resp.addCookie(cookie);
////		}
////		
////		resp.setContentType("text/html");
////		resp.setStatus(HttpServletResponse.SC_ACCEPTED);
////		resp.setHeader("fruit", "pomme");
//
//		resp.setContentType("text/html");
//		resp.setCharacterEncoding("UTF-8");
//		
////		String name = req.getParameter("name");
////		String lastName = req.getParameter("lastname");
//		
//
//		PrintWriter pw = resp.getWriter();
//		pw.println("<!DOCTYPE html>");
//		pw.println("<html lang=\"en\">");
//		pw.println("<head>");
//		pw.println("<meta charset=\"UTF-8\">");
//		pw.println("<meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">");
//		pw.println("<title>" + app + " - Accueil</title>");
//		pw.println("</head>");
//		pw.println("<body>");
//		
//		pw.println("<h1>Accueil<h1>");
////		pw.println("<h1>Bienvenue " + name + " " + lastName + " !</h1>");
//		
//		//Customer cust = (Customer) req.getAttribute("customer");
//		HttpSession session = req.getSession();
//		Customer cust = (Customer) session.getAttribute("customer");
//		if (cust != null) {
//			pw.println("<h1>Bienvenue " + cust.getFirstName() + " " + cust.getLastName() + " !</h1>");
//		}
//		pw.println("</body>");
//		pw.println("</html>");
//	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}
	
	@Override
	public void destroy() {
		super.destroy();
		System.out.println("HelloServlet s'arrête");
	}
}
