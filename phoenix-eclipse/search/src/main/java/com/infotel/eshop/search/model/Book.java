package com.infotel.eshop.search.model;

import java.time.LocalDate;
import java.util.List;


public class Book extends Product {
	private String isbn;
	private String language;
	private int pages;
	

	private LocalDate releaseDate;
	
	

	private List<Figure> authors; //
	


	public String getIsbn() {
		return isbn;
	}
	
	public String getLanguage() {
		return language;
	}
	
	public int getPages() {
		return pages;
	}
	
	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}
	
	public void setLanguage(String language) {
		this.language = language;
	}
	
	public void setPages(int pages) {
		this.pages = pages;
	}

	public LocalDate getReleaseDate() {
		return releaseDate;
	}

	public void setReleaseDate(LocalDate releaseDate) {
		this.releaseDate = releaseDate;
	}

	public List<Figure> getAuthors() {
		return authors;
	}

	public void setAuthors(List<Figure> authors) {
		this.authors = authors;
	}
	
	
}
