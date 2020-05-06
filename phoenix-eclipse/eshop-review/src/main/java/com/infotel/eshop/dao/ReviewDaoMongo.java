package com.infotel.eshop.dao;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.infotel.eshop.model.Review;
import com.mongodb.BasicDBObject;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.UpdateOptions;

@Repository
public class ReviewDaoMongo {

	private final static Logger log = LogManager.getLogger(ReviewDaoMongo.class);
	
	@Autowired
	private MongoClient client; 
	
	public void insertAndUpdate(Review review) {
		ObjectMapper mapper;	
		MongoDatabase database;

		mapper = new ObjectMapper();
		mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
		//request to insert or update doc in bases
		//db.reviews.update({username:"roger@gmail.com", productId: 1},{$set: {comment:"update AGain"}},{upsert:true})
		try {
			database = client.getDatabase("eshop");
			
			MongoCollection<Document> collection = database.getCollection("reviews");
			
			BasicDBObject newData = new BasicDBObject();
			
			BasicDBObject dataToChange = new BasicDBObject();
			dataToChange.append("comment", review.getComment());
			dataToChange.append("rating", review.getRating());
			dataToChange.append("date", LocalDateTime.now().toString());
			newData.append("$set", dataToChange);
			
			
//			BasicDBObject date = new BasicDBObject();
//			date.append("date",true);
//			date.append("offset","now.getTimezoneOffset()");
//			
//			newData.append("$currentDate", date);
			
			
			BasicDBObject query = new BasicDBObject();
			query.append("username", review.getUsername());
			query.append("productId", review.getProductId());
			
			UpdateOptions option = new UpdateOptions();
			option.upsert(true);
			
			collection.updateOne(query, newData, option);
			
			
//			MongoCollection<Document> collection = database.getCollection("reviews");
//			String json = mapper.writeValueAsString(review);
//			Document doc = Document.parse(json);
//			collection.update(Filters.and(Filters.eq("username",review.getUsername()),Filters.eq("username",review.getUsername())), 
//					new Document("$set", doc)); //insertOne(doc);
		} catch (Exception e) {
			log.error("Erreur insertion ou mise a jour des reviews", e);
		}
		//Filters.and(Filters.eq("username",review.getUsername()),Filters.eq("username",review.getUsername()));
	}
	
	public List<Review> findByProductId(int productId){
		List<Review> reviews = new ArrayList<>();
		
		ObjectMapper mapper;
		MongoDatabase database;
		
		mapper = new ObjectMapper();
		//mapper.setSerializationInclusion(Include.NON_NULL);
		
		database = client.getDatabase("eshop");
		MongoCollection<Document> collection = database.getCollection("reviews");

		for(Document doc : collection.find(Filters.eq("productId", productId))) {
			Review rv = new Review();
			//mapper.write(doc, Review.class);
			rv.setComment(doc.getString("comment"));
			//rv.setProductId(doc.getInteger("productId"));
			rv.setRating(doc.getInteger("rating"));
			rv.setUsername(doc.getString("username"));
			
			reviews.add(rv);
		}
		
		return reviews;
	}
	
}
