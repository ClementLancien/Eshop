package com.infotel.eshop.review;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@Configuration @ComponentScan("com.infotel.eshop")
@EnableMongoRepositories
public class ReviewConfig {


}
