package com.infotel.eshop.tools.es.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;


public class Product {
	private String type;
	
	@JsonIgnore
    protected int id;
    protected String name;
    protected String description;
    protected double price;
    private boolean available;
    
    private Date releaseDate;

    private String isbn;
    private String language;
    private int pages;
    private int length;
    private List<String> tags;
    private List<String> languages;

    protected ProductFamily family;

    protected int imageId;
    
    private List<Figure> authors;
    private List<Figure> actors;
    private Figure director;
    private List<Figure> artists;
    
    public int getArtistsSize() {
    	return artists == null ? 0 : artists.size();
    }
    
    public SuggestField getSuggest() {
    	return new SuggestField(name, 1);
    }
    
    public void addTag(String tag) {
    	if (tags == null) tags = new ArrayList<>();
    	tags.add(tag);
    }

    public void addMovieLanguage(String l) {
    	if (languages == null) languages = new ArrayList<>();
    	languages.add(l);
    }

    public void addAuthor(Figure author) {
    	if (authors == null) authors = new ArrayList<>();
    	authors.add(author);
    }

    public void addActor(Figure actor) {
    	if (actors == null) actors = new ArrayList<>();
    	actors.add(actor);
    }

    public void addArtist(Figure artist) {
    	if (artists == null) artists = new ArrayList<>();
    	artists.add(artist);
    }

    // getters and setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

	public ProductFamily getFamily() {
        return family;
    }

    public void setFamily(ProductFamily family) {
        this.family = family;
    }

    public int getImageId() {
		return imageId;
	}

	public void setImageId(int imageId) {
		this.imageId = imageId;
	}

	public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

	public Date getReleaseDate() {
		return this.releaseDate;
	}

	public void setReleaseDate(Date releaseDate) {
		this.releaseDate = releaseDate;
	}

	public String getIsbn() {
		return this.isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public String getLanguage() {
		return this.language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public int getPages() {
		return this.pages;
	}

	public void setPages(int pages) {
		this.pages = pages;
	}

	public int getLength() {
		return this.length;
	}

	public void setLength(int length) {
		this.length = length;
	}

	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public List<Figure> getAuthors() {
		return this.authors;
	}

	public void setAuthors(List<Figure> authors) {
		this.authors = authors;
	}

	public List<Figure> getActors() {
		return this.actors;
	}

	public void setActors(List<Figure> actors) {
		this.actors = actors;
	}

	public Figure getDirector() {
		return this.director;
	}

	public void setDirector(Figure director) {
		this.director = director;
	}

	public List<Figure> getArtists() {
		return this.artists;
	}

	public void setArtists(List<Figure> artists) {
		this.artists = artists;
	}

	public boolean isAvailable() {
		return this.available;
	}

	public void setAvailable(boolean available) {
		this.available = available;
	}

	public List<String> getLanguages() {
		return this.languages;
	}

	public void setLanguages(List<String> languages) {
		this.languages = languages;
	}

	public List<String> getTags() {
		return this.tags;
	}

	public void setTags(List<String> tags) {
		this.tags = tags;
	}
	
	
}
