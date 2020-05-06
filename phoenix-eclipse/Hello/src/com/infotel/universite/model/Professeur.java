package com.infotel.universite.model;

public class Professeur {
	public String nom;
	public String prenom;
	public Cours[] ensembleDeCours;
	public int nbreCoursMax;

	public Professeur(String nom, String prenom, int nbreCoursMax) {
		this.nom = nom;
		this.prenom = prenom;
		this.ensembleDeCours = new Cours[nbreCoursMax];
		this.nbreCoursMax = nbreCoursMax;
	}

	public void afficher() {
		System.out.println("Le nom du professeur est : " + this.nom);
		System.out.println("Le prenom du professeur est : " + this.prenom);
		for (int i = 0; i < this.ensembleDeCours.length; i++) {
			if (this.ensembleDeCours[i] != null) {
				System.out.print(this.ensembleDeCours[i].getNom() + " | ");
			} else { // seulement si on veut afficher l ensemble du tableau
				System.out.print("null | ");
			}
		}
		System.out.println();
	}

	public int getNbCoursMax() {
		return this.nbreCoursMax;
	}

//	public int getNbCoursEffectif() {
//		int nbCoursEffectif = 0;
//		System.out.println("test ! "+ this.ensembleDeCours.length);
//		System.out.println(this.ensembleDeCours[nbCoursEffectif] != null);
//		System.out.println(nbCoursEffectif < 10);
//		while (this.ensembleDeCours[nbCoursEffectif] == null && nbCoursEffectif < 10 ) {
////			nbCoursEffectif += 1;
//			System.out.println(nbCoursEffectif);
//			nbCoursEffectif++;
//		}
//		return nbCoursEffectif;
//	}
	public int getNbCoursEffectif() {
		int nb = 0;
		for (Cours cours : ensembleDeCours) {
			if (cours != null)
				nb++;
		}
		return nb;
	}

	public void ajouterCours(Cours cours) {
		int index = this.getNbCoursEffectif();
//		System.out.println(index);
		if (index < this.getNbCoursMax()) {
			this.ensembleDeCours[index] = cours;
		} else {
			System.out.println("Impossible d'ajouter un cours. Emploi du temps complet!!");
		}
	}

	public Cours getCours(int position) {
		if (position < 1 || position > this.nbreCoursMax) {
			System.out.println("Out of range");
		}
		int index = position - 1;
		return this.ensembleDeCours[index];
	}

	public int chercherPositionCours(Cours cours) {
		boolean exists = false;
		int i = 0;
		while (i < this.getNbCoursEffectif() && exists == false) {
			if (cours.equals(this.ensembleDeCours[i])) {
				exists = true;
			}
			i++;
		}
		if (exists) {
			return i;
		}
		return 0;
	}

	public Cours enleverCours(Cours cours) {
		int position = this.chercherPositionCours(cours);
		if (position == 0) {
			return null;
		}
		this.ensembleDeCours[position - 1] = this.ensembleDeCours[this.getNbCoursEffectif() - 1];
		this.ensembleDeCours[this.getNbCoursEffectif() - 1] = null;
		return cours;
	}

	// getters and setters
	public String getNom() {
		return nom;
	}

	public String getPrenom() {
		return prenom;
	}

	public Cours[] getEnsembleDeCours() {
		return ensembleDeCours;
	}

	public int getNbreCoursMax() {
		return nbreCoursMax;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public void setEnsembleDeCours(Cours[] ensembleDeCours) {
		this.ensembleDeCours = ensembleDeCours;
	}

	public void setNbreCoursMax(int nbreCoursMax) {
		this.nbreCoursMax = nbreCoursMax;
	}

}