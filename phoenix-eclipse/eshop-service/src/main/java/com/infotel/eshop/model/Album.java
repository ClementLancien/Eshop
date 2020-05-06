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
public class Album extends Product {
	private int length;
	
	@Column(name="release_date")
	private LocalDate releaseDate;
	
	//TODO A CHANGER car EAGER mauvaise methode c'est trop couteux(fetch = FetchType.LAZY)
	@ManyToMany //@LazyCollection(LazyCollectionOption.FALSE) 
	@JoinTable(
			name="album_artist",
			joinColumns = @JoinColumn(name="album_id"),
			inverseJoinColumns= @ JoinColumn(name="artist_id")
	)
	private List<Figure> artists;
	
//	@Override
//	public String toString() {
//		return "Album [length=" + length + ", releaseDate=" + releaseDate + ", id=" + id + ", name=" + name
//				+ ", description=" + description + ", price=" + price + "]";
//	}

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
