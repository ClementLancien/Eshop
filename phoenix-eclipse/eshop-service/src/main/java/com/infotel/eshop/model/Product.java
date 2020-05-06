package com.infotel.eshop.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedAttributeNode;
import javax.persistence.NamedEntityGraph;
import javax.persistence.OneToOne;

@Entity @Inheritance(strategy = InheritanceType.JOINED)
@NamedEntityGraph(
	name = "productFull",
	attributeNodes = {
			@NamedAttributeNode("family"),
			@NamedAttributeNode("tags")
	}
)
public class Product {
	@Id
	protected int id;
	protected String name;
	protected String description;
	protected double price;
	
	@ManyToOne(fetch = FetchType.LAZY) @JoinColumn(name="family_id")
	protected ProductFamily family;
	
	@OneToOne(fetch = FetchType.LAZY) @JoinColumn(name="image_id") 
	protected ProductImage image;
	
	@ManyToMany @JoinTable(
			name="product_product_tag",
			joinColumns = @JoinColumn(name="product_id"), // 1ere clé primaire
			inverseJoinColumns = @JoinColumn(name="tag_id") // la deuxieme
	)
	protected List<ProductTag> tags;
	
	@Override
	public boolean equals(Object obj) {
		if(obj == null) return false;
		Product p = (Product)obj;
		return this.id == p.id;
	}

	public int getId() {
		return id;
	}
	
	public String getName() {
		return name;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public void setName(String name) {
		this.name = name;
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

	public ProductFamily getFamily() {
		return family;
	}

	public void setFamily(ProductFamily family) {
		this.family = family;
	}

	public ProductImage getImage() {
		return image;
	}

	public void setImage(ProductImage image) {
		this.image = image;
	}

	public List<ProductTag> getTags() {
		return tags;
	}

	public void setTags(List<ProductTag> tags) {
		this.tags = tags;
	}
}