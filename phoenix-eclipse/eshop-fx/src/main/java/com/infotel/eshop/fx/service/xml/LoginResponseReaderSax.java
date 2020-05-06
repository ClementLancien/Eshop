package com.infotel.eshop.fx.service.xml;

import java.io.File;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import com.infotel.eshop.fx.model.User;

public class LoginResponseReaderSax {
	
	public User getResponse() throws Exception {
		
		SAXParserFactory factory = SAXParserFactory.newInstance();
		SAXParser parser = factory.newSAXParser();
		
		LoginResponseHandler handler = new LoginResponseHandler();
		parser.parse(new File("xml\\LoginResponse.xml"), handler);
		
		return handler.getUser();
	}

}
