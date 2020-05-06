
public class Chien {
	String nom;
	float poids;
	 static int ageMaxi;

	void nourrir(Nourriture repas) {
		poids += repas.poids;
		repas.poids = 0;
	}
}