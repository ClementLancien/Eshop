package com.infotel.eshop.tools.es.model.fr;

import com.infotel.eshop.tools.es.model.ProductFamily;

public class Famille {
	private int id;
	private String nom;

	public Famille(ProductFamily family) {
		this.id = family.getId();
		this.nom = family.getName();
	}

	// getters and setters
	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNom() {
		return this.nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}
	
}
