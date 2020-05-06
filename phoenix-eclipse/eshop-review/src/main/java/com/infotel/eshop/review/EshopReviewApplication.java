package com.infotel.eshop.review;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication @EnableMongoRepositories
public class EshopReviewApplication {

	public static void main(String[] args) {
		SpringApplication.run(EshopReviewApplication.class, args);
	}

}
