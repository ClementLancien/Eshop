package com.infotel.eshop.review.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController @RequestMapping("/hello")
public class HelloController {

	@GetMapping
	public String sayHello() {
		return "Hello from reviews";
	}
}
