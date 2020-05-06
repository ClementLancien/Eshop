package com.infotel.eshop.ws;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.support.ServletContextResource;
import org.springframework.ws.config.annotation.EnableWs;
import org.springframework.ws.wsdl.wsdl11.DefaultWsdl11Definition;
import org.springframework.ws.wsdl.wsdl11.Wsdl11Definition;
import org.springframework.xml.xsd.SimpleXsdSchema;
import org.springframework.xml.xsd.XsdSchema;

@Configuration @ComponentScan("com.infotel.eshop.ws")
@EnableWs
public class WebServiceConfig {
	
	@Bean @Autowired
	public XsdSchema eshopXsd(ServletContext context) {
		ServletContextResource resource =
				new ServletContextResource(context, "/WEB-INF/eshop.xsd");
		return new SimpleXsdSchema(resource);
	}
	
	@Bean @Autowired // nom de la methode tres important
	public Wsdl11Definition eshop(XsdSchema schema ) {
		DefaultWsdl11Definition wsdl = new DefaultWsdl11Definition();
		wsdl.setPortTypeName("EShopService");
		wsdl.setLocationUri("http://sem148.sesame.infotel.com:8080/eshop-ws/services"); // nom de notre machine ; eshop-ws context root; les services accesbiles a l'uri services
		wsdl.setSchema(schema);
		
		return wsdl;
	}
	
}
