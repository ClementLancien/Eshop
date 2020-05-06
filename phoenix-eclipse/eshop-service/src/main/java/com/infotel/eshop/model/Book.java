package com.infotel.eshop.model;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.PrimaryKeyJoinColumn;

@Entity @PrimaryKeyJoinColumn(name="id")
public class Book extends Product {
	private String isbn;
	private String language;
	private int pages;
	
	@Column(name="release_date")
	private LocalDate releaseDate;
	
	
	@ManyToMany //@LazyCollection(LazyCollectionOption.FALSE) 
	@JoinTable(
			name="book_author",
			joinColumns = @JoinColumn(name="book_id"), // foregin key
			inverseJoinColumns = @JoinColumn(name="author_id")
	)
	private List<Figure> authors; //
	
//	@Override
//	public String toString() {
//		return "Book [isbn=" + isbn + ", pages=" + pages + ", releaseDate=" + releaseDate
//				+ ", id=" + id + ", name=" + name + ", description=" + description + ", price=" + price + "]";
//	}

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
