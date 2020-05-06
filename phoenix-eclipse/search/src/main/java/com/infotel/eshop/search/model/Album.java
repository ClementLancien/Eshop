package com.infotel.eshop.search.model;

import java.time.LocalDate;
import java.util.List;


public class Album extends Product {
	private int length;
	

	private LocalDate releaseDate;
	

	private List<Figure> artists;
	

	public int getLength() {
		return length;
	}

	public void setLength(int length) {
		this.length = length;
	}

	public LocalDate getReleaseDate() {
		return releaseDate;
	}

	public void setReleaseDate(LocalDate releaseDate) {
		this.releaseDate = releaseDate;
	}

	public List<Figure> getArtists() {
		return artists;
	}

	public void setArtists(List<Figure> artists) {
		this.artists = artists;
	}
}
