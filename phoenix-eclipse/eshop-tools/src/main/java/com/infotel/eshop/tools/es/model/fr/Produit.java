package com.infotel.eshop.tools.es.model.fr;

import java.util.Date;
import java.util.List;

import com.infotel.eshop.tools.es.model.Product;

public class Produit {
	private int id;
    protected String titre;
    protected double prix;
    
    private Date date;

    private List<String> tags;

    protected Famille famille;

	public Produit() {
		
	}
		
	public Produit(Product p) {
		this.id = p.getId();
		this.titre = p.getName();
		this.prix = p.getPrice();
		this.date = p.getReleaseDate();
		this.tags = p.getTags();
		this.famille = new Famille(p.getFamily());
	}
	
	@Override
	public String toString() {
		return "Produit [id=" + this.id + ", titre=" + this.titre + ", prix="
				+ this.prix + ", date=" + this.date + ", tags=" + this.tags
				+ ", famille=" + this.famille + "]";
	}

	// getters and setters
	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitre() {
		return this.titre;
	}

	public void setTitre(String titre) {
		this.titre = titre;
	}

	public double getPrix() {
		return this.prix;
	}

	public void setPrix(double prix) {
		this.prix = prix;
	}

	public Date getDate() {
		return this.date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public List<String> getTags() {
		return this.tags;
	}

	public void setTags(List<String> tags) {
		this.tags = tags;
	}

	public Famille getFamille() {
		return this.famille;
	}

	public void setFamille(Famille famille) {
		this.famille = famille;
	}
	
}
