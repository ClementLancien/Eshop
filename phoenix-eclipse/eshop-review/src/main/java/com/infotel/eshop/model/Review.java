package com.infotel.eshop.model;

import java.time.LocalDateTime;

import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection="reviews")
public class Review {
	
	private int productId;
	private String username;
	private int rating;
	private String comment;
	private LocalDateTime date;
	
	public int getProductId() {
		return productId;
	}
	
	public String getUsername() {
		return username;
	}
	
	public int getRating() {
		return rating;
	}
	
	public String getComment() {
		return comment;
	}
	
	public void setProductId(int productId) {
		this.productId = productId;
	}
	
	public void setUsername(String username) {
		this.username = username;
	}
	
	public void setRating(int rating) {
		this.rating = rating;
	}
	
	public void setComment(String comment) {
		this.comment = comment;
	}
	
	public LocalDateTime getDate() {
		return date;
	}

	public void setDate(LocalDateTime date) {
		this.date = date;
	}

	@Override
	public String toString() {
		return "Review [productId=" + productId + ", username=" + username + ", rating=" + rating + ", comment="
				+ comment + "]";
	}
	
	
	
}
