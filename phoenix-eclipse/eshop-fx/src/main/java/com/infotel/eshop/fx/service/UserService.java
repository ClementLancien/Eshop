package com.infotel.eshop.fx.service;

import javax.security.sasl.AuthenticationException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.infotel.eshop.fx.model.User;
import com.infotel.eshop.fx.service.xml.LoginResponseReaderWS;

public class UserService {

	private final static Logger log = LogManager.getLogger(UserService.class);
	
	public User authenticate(String username, String password) throws AuthenticationException {
		if (log.isDebugEnabled()) {
			log.debug("Demande authentification : " + username + " / " + password);
		}
		
		//if ("clement".equals(username) && "secret".equals(password)) {
			//LoginResponseReaderSax reader = new LoginResponseReaderSax();
			//LoginResponseDom reader = new LoginResponseDom();
			LoginResponseReaderWS reader = new LoginResponseReaderWS();
			try {
//				User user = reader.getResponse();
//				return user;
				return reader.getResponse(username, password);
			} catch (Exception e) {
				log.error("Echec du reader de l'authentification de l'utilisateur", e);
				//e.printStackTrace(); // a remplacer par un log
			}
			
			
//			User user = new User();
//			user.setUsername(username);
//			user.setFirstName("Clement");
//			user.setLastName("Lancien");
//			return user;
		//}
		
		throw new AuthenticationException("Echec de l'authentification");
	}
}
