package com.infotel.eshop.search.model;

import java.time.LocalDate;
import java.util.List;


public class Movie extends Product {
	private int length;
	

	private LocalDate releaseDate;
	
	private List<String> languages;
	

	private List<Figure> actors;
	

	private Figure director;
	


	public int getLength() {
		return length;
	}

	public List<String> getLanguages() {
		return languages;
	}

	public void setLength(int length) {
		this.length = length;
	}

	public void setLanguages(List<String> languages) {
		this.languages = languages;
	}

	public LocalDate getReleaseDate() {
		return releaseDate;
	}

	public void setReleaseDate(LocalDate releaseDate) {
		this.releaseDate = releaseDate;
	}

	public List<Figure> getActors() {
		return actors;
	}

	public void setActors(List<Figure> actors) {
		this.actors = actors;
	}

	public Figure getDirector() {
		return director;
	}

	public void setDirector(Figure director) {
		this.director = director;
	}
	
}
