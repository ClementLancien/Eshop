package com.infotel.eshop.model;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.PrimaryKeyJoinColumn;

@Entity @PrimaryKeyJoinColumn(name="id")
public class Movie extends Product {
	private int length;
	
	@Column(name="release_date")
	private LocalDate releaseDate;
	
	@ElementCollection @CollectionTable(
		name = "movie_language",
		joinColumns = @JoinColumn(name="movie_id")
	)
	@Column(name="language")
	private List<String> languages;
	
	//TODO A CHANGER car EAGER mauvaise methode c'est trop couteux
	@ManyToMany //@LazyCollection(LazyCollectionOption.FALSE) 
	@JoinTable(
			name="movie_actor",
			joinColumns = @JoinColumn(name="movie_id"), // foreign key
			inverseJoinColumns = @JoinColumn(name="actor_id")
	)
	private List<Figure> actors;
	
	@ManyToOne @JoinTable( // on raisonne en joinTable pr avoir de la souplesse d'évolution si on passe en ManyToMany
			name="movie_director",
			joinColumns = @JoinColumn(name="movie_id"), // foreign key
			inverseJoinColumns = @JoinColumn(name="director_id")
	)
	private Figure director;
	
//	@Override
//	public String toString() {
//		return "Movie [length=" + length + ", releaseDate=" + releaseDate + "]";
//	}

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
