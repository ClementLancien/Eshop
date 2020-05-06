package com.infotel.eshop.review.service;

import java.util.List;

import com.infotel.eshop.model.Review;


public interface ReviewService {
	public void insertAndUpdateReview(Review review);
	
	public List<Review> findReviewByProductId(int productId);
}
