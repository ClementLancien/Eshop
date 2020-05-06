package com.infotel.eshop.mvc;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration @ComponentScan("com.infotel.eshop.mvc")
@EnableWebMvc // active moteur d'annotation pr les @Controller
public class WebConfig implements WebMvcConfigurer { // implements WebMvcConfigurer pr nous donner des faciliter plus tard

//	@Bean
//	public ViewResolver viewResolver() {
//		InternalResourceViewResolver resolver = 
//				new InternalResourceViewResolver("/WEB-INF/jsp/", ".jsp");
//		return resolver;
//	}
	
	//ou redefinir cette methode
	@Override
	public void configureViewResolvers(ViewResolverRegistry registry) {
		registry.jsp("/WEB-INF/jsp/", ".jsp");
	}
	
	@Bean
	public MessageSource messageSource() {
		ResourceBundleMessageSource source = new ResourceBundleMessageSource();
		source.setBasename("com.infotel.eshop.mvc.i18n.Translate");
		
		return source;
	}
	
}
