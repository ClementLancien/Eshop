package com.infotel.eshop.review.controller;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.infotel.eshop.model.Review;
import com.infotel.eshop.review.service.ReviewService;


@RestController @RequestMapping("/reviews")
public class ReviewController {
	
	private final static Logger log = LogManager.getLogger(ReviewController.class);
	
	@Autowired
	private ReviewService service;

	
	@PutMapping
	public void insertReviews(@RequestBody Review review) {
		//if ()
		 service.insertAndUpdateReview(review);
	}
	
	@GetMapping("/{productId}")
	public List<Review> findReviewsByProductId(@PathVariable int productId) {
		return service.findReviewByProductId(productId);
	}
	

}
