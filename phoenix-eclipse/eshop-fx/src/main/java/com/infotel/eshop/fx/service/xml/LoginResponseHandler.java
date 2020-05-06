package com.infotel.eshop.fx.service.xml;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import com.infotel.eshop.fx.model.User;

public class LoginResponseHandler extends DefaultHandler {
	private User user;
	
	private String currentElt;
	
	@Override
	public void startDocument() throws SAXException {
		//System.out.println("START DOC");
		user = new User();
	}
	
	@Override
	public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
		//System.out.println("START ELE : " + qName);
		currentElt = qName;
	}
	
	@Override
	public void characters(char[] ch, int start, int length) throws SAXException {
		String str = new String(ch, start, length);
		//System.out.println(str);
		
		if (currentElt == null) return;
		switch (currentElt) {
		case "Username":
			user.setUsername(str);
//			currentElt = null; // sinon on recupere les autres caracteres blancs. Puisque l'on repeter cette ligne la a chaque case on le deplace dans la methode endElement
			break;
		case "FirstName":
			user.setFirstName(str);
//			currentElt = null;
			break;
		case "LastName":
			user.setLastName(str);
//			currentElt = null;
			break;
		}
	}
	
	@Override
	public void endElement(String uri, String localName, String qName) throws SAXException {
		//System.out.println("END ELE : " + qName);
		currentElt = null;
	}
	
	@Override
	public void endDocument() throws SAXException {
		//System.out.println("END DOC" + user);
	}

	public User getUser() {
		return user;
	}
	
}
