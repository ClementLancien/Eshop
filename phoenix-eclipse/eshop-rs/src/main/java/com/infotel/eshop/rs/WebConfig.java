package com.infotel.eshop.rs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import okhttp3.OkHttpClient;

@Configuration @ComponentScan("com.infotel.eshop.rs")
@EnableWebMvc
public class WebConfig implements WebMvcConfigurer {
	
	   @Bean
	    public OkHttpClient httpClient() {
	        return new OkHttpClient();
	    }
	
}
