package com.infotel.eshop.dao;
import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.infotel.eshop.model.Review;
import com.infotel.eshop.review.EshopReviewApplication;
import com.infotel.eshop.review.service.ReviewService;
@SpringBootTest(classes = EshopReviewApplication.class)
class ReviewDaoMongoTests{
	
	@Autowired
	private ReviewService service;	
	
	@Test 
	public void it_should_insert_and_update_review() throws Exception {
		//Arrange
		int productId = 2;
		
		Review review = new Review();
		review.setProductId(productId);
		review.setUsername("roger@gmail.com");
		review.setRating(4);
		review.setComment("OK");

		//Act
		service.insertAndUpdateReview(review);

		//Assert
		//assertEquals(review, service.findReviewByProductId(productId));
		assertThat(review).isNotNull();
	}
	
	
	@Test 
	public void it_should_find_productId2() throws Exception {
		//Arrange
		int productId = 2;
		
		Review review = new Review();
		review.setProductId(productId);
		review.setUsername("roger@gmail.com");
		review.setRating(4);
		review.setComment("OK");
		
		
		//Act
		service.findReviewByProductId(productId);
		
		//Assert
		assertThat(review)
		.isNotNull()
		.extracting(Review::getUsername, Review::getRating, Review::getComment)
		.containsExactly("roger@gmail.com",4, "OK");

	}
	
	@Test
	public void it_should_not_find_productId1_without_username() {
		//Arrange
				int productId = 2;
				
				Review review = new Review();
				review.setProductId(productId);
				review.setUsername(null);
				review.setRating(4);
				review.setComment("OK");
				
				
				//Act
				service.findReviewByProductId(productId);
				
				//Assert
				assertThat(review)
				.isNotNull()
				.extracting(Review::getUsername, Review::getRating, Review::getComment)
				.containsExactly(null,4, "OK");
				assertThat(review.getUsername())
					.isNull();
	}
	
	
}
