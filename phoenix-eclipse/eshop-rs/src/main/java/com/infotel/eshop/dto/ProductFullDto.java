package com.infotel.eshop.dto;

import java.time.LocalDate;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.infotel.eshop.jackson.LocalDateDeserializer;
import com.infotel.eshop.jackson.LocalDateSerializer;
import com.infotel.eshop.jaxb.LocalDateAdapter;

@XmlRootElement(name="Product")
@XmlAccessorType(XmlAccessType.FIELD) // pour quill puisse lire XmlElement
@JsonInclude(value= Include.NON_EMPTY, content = Include.NON_NULL)
public class ProductFullDto {
	@XmlAttribute
	private int id;
	
	@XmlAttribute
	private String type;
	
	@XmlElement(name="Name")
	private String name;
	
	@XmlElement(name="Description")
	private String description;
	
	@XmlElement(name="Price")
	private double price;

	@XmlElement(name="ImageId")
	private Integer imageId;
	
	@XmlElement(name="ReleaseDate")
	@XmlJavaTypeAdapter(LocalDateAdapter.class)
	@JsonSerialize(using = LocalDateSerializer.class)
	@JsonDeserialize(using = LocalDateDeserializer.class)
	private LocalDate releaseDate;
	
	@XmlElement(name="Family")
	private ProductFamilyDto family;
	
	@XmlElement(name="Tag")
	private List<ProductTagDto> tags;
	
	// specific book
	
	@XmlElement(name="Isbn")
	private String isbn;
	
	@XmlElement(name="Language")
	private String language;
	
	@XmlElement(name="Pages") 
	private Integer pages; // si int on aura pages dans movie and album
	
	@XmlElement(name="Author")
	@XmlElementWrapper(name="Authors")
	private List<FigureDto> authors;
	
	// specific movie
	
	@XmlElement(name="Length")
	private Integer lengthMovie;
	
	@XmlElement(name="Language")
	@XmlElementWrapper(name="Languages")
	private List<String> languages;
	
	@XmlElement(name="Actor")
	@XmlElementWrapper(name ="Actors")
	private List<FigureDto> actors;
	
	@XmlElement(name="Director")
	private FigureDto director;
	
	//specific album
	
	@XmlElement(name="Length")
	private Integer lengthAlbum;
	
	@XmlElement(name="Artist")
	@XmlElementWrapper(name="Artists")
	private List<FigureDto> artists;
	
	@Override
	public String toString() {
		return "ProductDto [id=" + id + ", name=" + name + ", description=" + description + ", price=" + price
				+ ", family=" + family + ", imageID=" + imageId + "]";
	}

	public int getId() {
		return id;
	}

	public String getType() {
		return type;
	}

	public String getName() {
		return name;
	}

	public String getDescription() {
		return description;
	}

	public double getPrice() {
		return price;
	}

	public Integer getImageId() {
		return imageId;
	}

	public LocalDate getReleaseDate() {
		return releaseDate;
	}

	public ProductFamilyDto getFamily() {
		return family;
	}

	public List<ProductTagDto> getTags() {
		return tags;
	}

	public String getIsbn() {
		return isbn;
	}

	public String getLanguage() {
		return language;
	}

	public Integer getPages() {
		return pages;
	}

	public List<FigureDto> getAuthors() {
		return authors;
	}

	public Integer getLengthMovie() {
		return lengthMovie;
	}

	public List<String> getLanguages() {
		return languages;
	}

	public List<FigureDto> getActors() {
		return actors;
	}

	public FigureDto getDirector() {
		return director;
	}

	public Integer getLengthAlbum() {
		return lengthAlbum;
	}

	public List<FigureDto> getArtists() {
		return artists;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setType(String type) {
		this.type = type;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public void setImageId(Integer imageId) {
		this.imageId = imageId;
	}

	public void setReleaseDate(LocalDate releaseDate) {
		this.releaseDate = releaseDate;
	}

	public void setFamily(ProductFamilyDto family) {
		this.family = family;
	}

	public void setTags(List<ProductTagDto> tags) {
		this.tags = tags;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public void setPages(Integer pages) {
		this.pages = pages;
	}

	public void setAuthors(List<FigureDto> authors) {
		this.authors = authors;
	}

	public void setLengthMovie(Integer lengthMovie) {
		this.lengthMovie = lengthMovie;
	}

	public void setLanguages(List<String> languages) {
		this.languages = languages;
	}

	public void setActors(List<FigureDto> actors) {
		this.actors = actors;
	}

	public void setDirector(FigureDto director) {
		this.director = director;
	}

	public void setLengthAlbum(Integer lengthAlbum) {
		this.lengthAlbum = lengthAlbum;
	}

	public void setArtists(List<FigureDto> artists) {
		this.artists = artists;
	}

}
