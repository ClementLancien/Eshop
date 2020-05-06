package com.infotel.universite.model;

public class ProfesseurCorrection {
	String nom;
	String prenom;

	Cours[] listeCours;

	public ProfesseurCorrection(String nom, String prenom, int nbCoursMax) {
		super();
		this.nom = nom;
		this.prenom = prenom;
		this.listeCours = new Cours[nbCoursMax];
	}

	void afficher() {
		System.out.println("Professeur : " + nom + " " + prenom);
//		for (int i = 0; i < listeCours.length; i++) {
//			Cours c = listeCours[i];
//			c.afficher();
//		}

//		for each moins verbeux (preferable de lutiliser ici)
		for (Cours cours : listeCours) {
			if (cours != null)
				cours.afficher();
		}
	}

	int getNbCoursMax() {
		return listeCours.length;
	}

	int getNbCoursEffectifs() {
		int nb = 0;
		for (Cours cours : listeCours) {
			if (cours != null)
				nb++;
		}
		return nb;
	}

//	void ajouter (Cours cours) { // faire avec boucle for car a ce stade on ne sait pas si il y aura des cases vides entre deux cours
//		for (int i = 0; i < listeCours.length; i++) {
//			if (listeCours[i] == null) {
//				listeCours[i] = cours;
//				break;
//			}
//		}
//	}

	void ajouter(Cours cours) { // autre implementation
		if (getNbCoursEffectifs() < getNbCoursMax()) {
			listeCours[getNbCoursEffectifs()] = cours;
		}
	}

	Cours getCours(int position) {
		if (position > getNbCoursMax() || position < 1)
			return null;
		return listeCours[position - 1];
	}

	int chercherPositionCours(String code) {
		for (int i = 0; i < getNbCoursEffectifs(); i++) {
			if (listeCours[i].getCode() == code) {
				return i + 1;
			}
		}
		return 0;
	}

	Cours enleverCours(String code) {
		int pos = chercherPositionCours(code);
		if (pos > 0) {
			Cours dernier = listeCours[getNbCoursEffectifs() - 1];
			Cours cours = listeCours[pos - 1];
			if (cours.getCode() != dernier.getCode()) {
				listeCours[pos - 1] = dernier;
				listeCours[getNbCoursEffectifs() - 1] = null;
			} else {
				listeCours[pos - 1] = null;
			}
			return cours;
		}
		return null;
	}

	public static void main(String[] args) {
		Cours cours1 = new Cours("HG01", "Histoire, Géographie", 2);
		Cours cours2 = new Cours("MA05", "Mathématiques", 1);
		Cours cours3 = new Cours("PH02", "Physique", 4);

		cours1.afficher();
		cours2.afficher();

		System.out.println(); // ou System.out.println("\r\n")
		ProfesseurCorrection prof = new ProfesseurCorrection("Léonard", "Vinci", 3);
		prof.afficher();

		System.out.println("Nb cours max : " + prof.getNbCoursMax());
		System.out.println("Nb cours effectifs : " + prof.getNbCoursEffectifs());

		System.out.println();
		prof.ajouter(cours1);
		prof.ajouter(cours2);
		prof.ajouter(cours3);
//		prof.ajouter(cours2);
//		prof.ajouter(cours2);
//		prof.ajouter(cours2);
//		prof.ajouter(cours2); stresser le systeme
//		prof.ajouter(cours2);
//		prof.ajouter(cours2);
//		prof.ajouter(cours2);
//		prof.ajouter(cours2);
//		prof.ajouter(cours2);
		prof.afficher();

		System.out.println("Nb cours max : " + prof.getNbCoursMax());
		System.out.println("Nb cours effectifs : " + prof.getNbCoursEffectifs());

//		System.out.println();
//		Cours c = prof.getCours(10);
//		c.afficher();

		System.out.println();
		int pos = prof.chercherPositionCours("MA05");
		System.out.println("Position du cours : " + pos);

		System.out.println();
		Cours coursRetire = prof.enleverCours("MA05");
		System.out.println("Le cours retire : " + coursRetire.getNom());
		System.out.println("\r\nLe prof : ");
		prof.afficher();
	}
}
