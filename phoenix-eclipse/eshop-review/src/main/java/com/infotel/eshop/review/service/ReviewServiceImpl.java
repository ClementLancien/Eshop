package com.infotel.eshop.review.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.infotel.eshop.dao.ReviewDaoMongo;
import com.infotel.eshop.model.Review;

@Service
public class ReviewServiceImpl implements ReviewService {

	@Autowired
	private ReviewDaoMongo reviewDaoMongo;
	
//	@Autowired 
//	private ReviewDao daoTest;
	
	@Override
	public void insertAndUpdateReview(Review review) {
		//daoTest.save(review);
		reviewDaoMongo.insertAndUpdate(review);
	}

	@Override
	public List<Review> findReviewByProductId(int productId) {
		return reviewDaoMongo.findByProductId(productId);
	}
	

}
