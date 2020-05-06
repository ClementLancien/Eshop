package com.infotel.eshop.fx.service.xml;

import java.io.File;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import com.infotel.eshop.fx.model.User;

public class LoginResponseReaderDom4j {
	public User getResponse() throws Exception {
		User user = new User();
		
		SAXReader reader = new SAXReader();
		Document doc = reader.read(new File("xml\\LoginResponse.xml"));
		
		//VERIFICATION parser
		//System.out.println(doc.asXML());
		
		Element rootElt = doc.getRootElement();
		
		
		// OBTENIR contenu text d'un élément
		
		//methode 1
		//Element usernameElt = rootElt.element("Username");
		//String username = usernameElt.getTextTrim();
		
		//methode 2
		//String username = rootElt.elementText("Username");
		//String firstName = rootElt.elementText("FirstName");
		//String lastName = rootElt.elementText("LastName");
		
		//user.setUsername(username);
		//user.setFirstName(firstName);
		//user.setLastName(lastName);
		
		//méthode3
		user.setUsername(rootElt.elementText("Username"));
		user.setFirstName(rootElt.elementText("FirstName"));
		user.setLastName(rootElt.elementText("LastName"));
		
		
//		Element usernameElt = rootElt.element("Username");
//		Attribute att = usernameElt.attribute("id");
//		String id = att.getValue();
//		
//		OU
//		String id = usernameElt.attributeValue("id");
//		usernameElt.elements
//		
		return user;
	}
	
}
