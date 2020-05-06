package com.infotel.eshop.fx.service.xml;

import javax.xml.transform.Source;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.DocumentResult;
import org.dom4j.io.DocumentSource;
import org.springframework.ws.client.core.WebServiceTemplate;

import com.infotel.eshop.fx.model.User;

public class LoginResponseReaderWS {
	
	private final static Logger log = LogManager.getLogger(LoginResponseReaderWS.class);
	
	public User getResponse(String username, String password) throws Exception {
//		String request = "<LoginRequest xmlns=\"http://www.infotel.com/eshop\">" + 
//						 "<Username>" + username + "</Username>" + 
//						 "<Password>" + password + "</Password>" + 
//						 "</LoginRequest>";
//		
		Document docReq = DocumentHelper.createDocument();
		Element rootElt = docReq.addElement("LoginRequest", "http://www.infotel.com/eshop");
		rootElt.addElement("Username").setText(username);
		rootElt.addElement("Password").setText(password);
		
//		Source source = new StreamSource(new StringReader(request));
//		Result result = new StreamResult(System.out);
		
		Source source = new DocumentSource(docReq);
		DocumentResult result = new DocumentResult();
		
		WebServiceTemplate client = new WebServiceTemplate();
		client.setDefaultUri("http://sem148.sesame.infotel.com:8080/eshop-ws/services");
		client.sendSourceAndReceiveToResult(source, result);

		Document docResp = result.getDocument();
		Element respElt = docResp.getRootElement();
		
		User user = new User();
		user.setUsername(respElt.elementText("Username"));
		user.setFirstName(respElt.elementText("FirstName"));
		user.setLastName(respElt.elementText("LastName"));
		
		if (log.isDebugEnabled()) {
			log.debug("Reader user : " + user);
		}
		
		return user;
	}
	
}
