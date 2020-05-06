package com.infotel.eshop.dto;

public class ReviewDto {

	private String username;
	private int productId;
	private String comment;
	private int rating;
	
	public String getUsername() {
		return username;
	}
	
	public int getProductId() {
		return productId;
	}
	
	public String getComment() {
		return comment;
	}
	
	public int getRating() {
		return rating;
	}
	
	public void setUsername(String username) {
		this.username = username;
	}
	
	public void setProductId(int productId) {
		this.productId = productId;
	}
	
	public void setComment(String comment) {
		this.comment = comment;
	}
	
	public void setRating(int rating) {
		this.rating = rating;
	}
	
}
