package com.infotel.universite.model;

public class Cours {
	private String code;
	private String nom;
	private int duree;
	
	public Cours(String code, String nom, int duree){
		this.code=code;
		this.nom=nom;
		this.duree=duree;
	}
	
	public void afficher() {
//		System.out.println("Ce cours a pour nom : " + this.code);
//		System.out.println("Ce cours a pour code : " + this.nom);
//		System.out.println("Ce cours a une durée de : " + this.duree);
		
		System.out.println("Code = " + code + ", nom = " + nom + ", durée = " + duree);
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public int getDuree() {
		return duree;
	}

	public void setDuree(int duree) {
		if (duree > 0) {
			this.duree = duree;
		}
		
	}
	
	
}