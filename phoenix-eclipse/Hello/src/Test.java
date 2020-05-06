import com.infotel.universite.model.Cours;
import com.infotel.universite.model.Professeur;

public class Test {
	public static void main(String[] args) {
//		Cours cours1 = new Cours();
//		Cours cours2 = new Cours();
//		cours1.nom = "Java";
//		cours1.code = "1";
//		cours2.nom = "SQL";
//		cours2.code = "2";
//		System.out.println(cours1.nom);
//		System.out.println(cours2.nom);
//		cours1.afficher();
//		cours2.afficher();

		Cours cours1 = new Cours("1", "Java", 11);
		Cours cours2 = new Cours("2", "SQL", 14);
		Cours cours3 = new Cours("3", "HTML", 11);
		Cours cours4 = new Cours("4", "CSS", 14);
		Cours cours5 = new Cours("5", "Hibernate", 11);
		Cours cours6 = new Cours("6", "Spring", 14);
		cours1.afficher();
		cours2.afficher();

		Professeur prof1 = new Professeur("Hanny", "Olivier", 10);
//		prof1.afficher();
		prof1.ajouterCours(cours1);
		prof1.ajouterCours(cours2);
		prof1.ajouterCours(cours3);
		prof1.ajouterCours(cours4);
		prof1.ajouterCours(cours5);
		prof1.ajouterCours(cours6);
		prof1.ajouterCours(cours5);
		prof1.ajouterCours(cours6);
		prof1.ajouterCours(cours5);
		prof1.ajouterCours(cours6);
		prof1.ajouterCours(cours5);
		prof1.ajouterCours(cours6);
		prof1.afficher();
//		System.out.println(prof1.getNbCoursEffectif());
//		System.out.println(prof1.chercherPositionCours(cours1));
		prof1.enleverCours(cours1);
		prof1.afficher();
		System.out.println(prof1.getNbCoursEffectif());

	}
}
