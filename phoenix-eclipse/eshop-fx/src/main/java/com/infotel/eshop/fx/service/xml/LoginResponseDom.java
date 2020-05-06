package com.infotel.eshop.fx.service.xml;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

import com.infotel.eshop.fx.model.User;

public class LoginResponseDom {
	
	public User getResponse() throws Exception{
		User user = new User();
		
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = factory.newDocumentBuilder();
		// création d'un document à partir d'un fichier
		Document doc = builder.parse("xml\\LoginResponse.xml");
	
		Element root = doc.getDocumentElement();
		for (int i = 0; i < root.getChildNodes().getLength(); i++) {
			Node node = root.getChildNodes().item(i);
			if (node.getNodeType() == Node.ELEMENT_NODE) {
				Element elt = (Element)node;
				switch(elt.getNodeName()) {
				case "Username":
					user.setUsername(elt.getTextContent());
					break;
				case "FirstName":
					user.setFirstName(elt.getTextContent());
					break;
				case "LastName":
					user.setLastName(elt.getTextContent());
					break;
				}
			}
		}
//		user.setUsername(doc.getElementsByTagName("Username").item(0).getTextContent());
//		user.setFirstName(doc.getElementsByTagName("FirstName").item(0).getTextContent());
//		user.setLastName(doc.getElementsByTagName("LastName").item(0).getTextContent());
		
//		NodeList nodes = document.getChildNodes();
//		User user2 = new User();
//		user2.setUsername(nodes.item(0).getTextContent());
//		user2.setFirstName(nodes.item(1).getTextContent());
//		user2.setLastName(nodes.item(2).getTextContent());
//		System.out.println(user2);
		return user;
	}
}
