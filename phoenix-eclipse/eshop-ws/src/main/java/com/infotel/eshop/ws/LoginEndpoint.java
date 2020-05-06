package com.infotel.eshop.ws;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import com.infotel.eshop.exception.AuthenticationException;
import com.infotel.eshop.exception.EShopException;
import com.infotel.eshop.model.Employee;
import com.infotel.eshop.service.UserService;

@Endpoint
public class LoginEndpoint {

	private final UserService service;

	//injection par constructeur
	public LoginEndpoint(UserService service) {
		super();
		this.service = service;
	}
	
	@PayloadRoot(localPart="LoginRequest", namespace="http://www.infotel.com/eshop")
	@ResponsePayload
	public Element handleLogin(@RequestPayload Element request) throws AuthenticationException, EShopException {
		//XML => java
		String username = request.elementText("Username");
		String password = request.elementText("Password");
		
		//Invoquer le service métier
		Employee emp = service.authenticateEmployee(username, password);
		
		//Java => XML
		Document docResp = DocumentHelper.createDocument();
		Element respElt = docResp.addElement("LoginResponse", "http://www.infotel.com/eshop"); // uri egal a targetNamespace dans wsdl
		respElt.addElement("Username").setText(emp.getUsername());
		respElt.addElement("FirstName").setText(emp.getFirstName());
		respElt.addElement("LastName").setText(emp.getLastName());
		return respElt;
	}
}
