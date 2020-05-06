package com.infotel.eshop.web;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.infotel.eshop.exception.AuthenticationException;
import com.infotel.eshop.exception.EShopException;
import com.infotel.eshop.model.Customer;
import com.infotel.eshop.service.UserService;

@WebServlet(urlPatterns = "/login", loadOnStartup = 1)
public class LoginServlet extends HttpServlet {
	
	private static final Logger log = LogManager.getLogger(LoginServlet.class);
	private static ApplicationContext ctx;
	
	@Override
	public void init() throws ServletException {
		ctx = WebApplicationContextUtils.getWebApplicationContext(getServletContext());
	}
//	@Override
//	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//		
//		resp.setContentType("text/html");
//		resp.setCharacterEncoding("UTF-8");
//		
//		PrintWriter pw = resp.getWriter();
//		pw.println("<!DOCTYPE html>");
//		pw.println("<html lang=\"en\">");
//		pw.println("<head>");
//		pw.println("<meta charset=\"UTF-8\">");
//		pw.println("<meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">");
//		pw.println("<title>Authentification</title>");
//		pw.println("</head>");
//		pw.println("<body>");
//		
//		pw.println("<form action=\"login\" method=\"POST\">");
//		pw.println("<input type=\"text\" name=\"username\" placeholder=\"Identifiant\">");
//		pw.println("<input type=\"password\" name=\"password\" placeholder=\"Mot de passe\">");
//		pw.println("<input type=\"submit\" value=\"Se connecter!\">");
//		pw.println("</form>");
//		
////		Boolean authFailed = 
////				(req.getAttribute("authFailed") == null)?false:(Boolean)req.getAttribute("authFailed");
////		if (authFailed) {
////			pw.println("<span style=\"color:red;\"> Echec de l'authentification </span>");
////		}
//		boolean authFailed =  req.getAttribute("authFailed") != null;
////		if (authFailed) {
//		if (req.getAttribute("authFailed") != null) {
//			pw.println("<span style=\"color:red;\"> Echec de l'authentification </span>");
//		}
//		
//		pw.println("</body>");
//		pw.println("</html>");
//	
//	}
	
//	@Override
//	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//		RequestDispatcher rd = req.getRequestDispatcher("login.jsp");
//		rd.forward(req, resp);
//	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String username = req.getParameter("username");
		String password = req.getParameter("password");
		
		//log.info("xxx");
		if (log.isDebugEnabled()) {
			log.debug("Demande authentification : " + username + " / " + password);
		}
		
		//UserService service = new UserServiceImpl();
//		ApplicationContext ctx = 
//				WebApplicationContextUtils.getWebApplicationContext(getServletContext());
		//UserService service = (UserService) ctx.getBean("userService");
		UserService service = ctx.getBean(UserService.class);
		//Customer cust = null;
		try {
			Customer cust = service.authenticateCustomer(username, password);
			//req.setAttribute("customer", cust);
			HttpSession session = req.getSession();
			//session.setMaxInactiveInterval(10*1); //Java Session Timeout https://www.baeldung.com/servlet-session-timeout
			session.setAttribute("customer", cust);
			
			if (log.isDebugEnabled()) {
				log.debug("Utilisateur connecté : " + cust);
				log.debug("Il vient du panier fromBasket : " + req.getAttribute("fromBasket"));
				log.debug(req.getRequestURI());
			}
			
			//RequestDispatcher rd = req.getRequestDispatcher("/home");
			//rd.forward(req, resp);
			if(req.getParameter("fromBasket") != null) {
				resp.sendRedirect("basket.jsp");
			} else {
				resp.sendRedirect("home.jsp");
			}
			
//			PrintWriter pw = resp.getWriter();
//			pw.println("<!DOCTYPE html>");
//			pw.println("<html lang=\"en\">");
//			pw.println("<head>");
//			pw.println("<meta charset=\"UTF-8\">");
//			pw.println("<meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">");
//			pw.println("<title>Authentification</title>");
//			pw.println("</head>");
//			pw.println("<body>");
//			
//			pw.println("Bienvenue " + cust.getFirstName() + " " + cust.getLastName());
//			
//			pw.println("</body>");
//			pw.println("</html>");
			
		} catch (AuthenticationException | EShopException e) {
			log.error("Echec de l'authentification", e);
			
			//req.setAttribute("authFailed", true);
			req.setAttribute("authFailed", "true");
			//doGet(req, resp);
			RequestDispatcher rd = req.getRequestDispatcher("/login.jsp");
			rd.forward(req, resp);
		}
	}
}
