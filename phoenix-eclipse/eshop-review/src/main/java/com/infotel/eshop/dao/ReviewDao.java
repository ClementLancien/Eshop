package com.infotel.eshop.dao;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.infotel.eshop.model.Review;

@Repository
public interface ReviewDao extends MongoRepository<Review, Integer> {

	
}
