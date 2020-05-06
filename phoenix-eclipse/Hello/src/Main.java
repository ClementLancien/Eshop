
public class Main {
	public static void main(String[] args) {
//		Sans constructeur
//		Voiture v = new Voiture();
//		v.modele = "Twingo";
//		System.out.println(v.avancer());

//		int i = 10;
//		int j = i;
//		j = j + 1;
//		System.out.println("la valeur de i est : " + i);
//		System.out.println("la valeur de j est : " + j);

//		Avec constructeur
//		Voiture v2 = new Voiture("Twingo", 10, 10000);
//		v2.afficher();
//		v2.setModele("Fiesta");
//		v2.afficher();
//		System.out.println(v2.getPrice());

//		Voiture v = null;
////		System.out.println("Modèle de la voiture : " + v.modele);
//
//		if (v2 != null) {
//			System.out.println("Modèle de la voiture : " + v2.modele);
//		}
//		
		
//		Voiture voiture1 = new Voiture("Fiesta", 5, 10000);
//		voiture1.afficher();
//		voiture1.discount(10);
//		voiture1.afficher();
//		voiture1.discount(-10);
//		voiture1.afficher();
		
		
//		Slide 84

//		// instanciation
//		Chien monChien = new Chien();
//		
//		// accès à la variable d'instance
//		String nomDeMonChien = monChien.nom;
//		
//		// modifications
//		nomDeMonChien = "Milou";
//		
//
//		// valeur de monChien.nom ? 
//		System.out.println(monChien.nom); --> null
//		
//		Chien autreChien = monChien;
//		autreChien.nom = "Idéfix";
//		
//
//		// valeur de monChien.nom et autreChien.nom ?
//		System.out.println(monChien.nom);   --> Idéfix
//		System.out.println(autreChien.nom); --> Idéfix
//		
//		autreChien = new Chien();
//		autreChien.nom = "Snoopy";
//		
//		// valeur de monChien.nom et autreChien.nom ?
//		System.out.println(monChien.nom);   --> Idéfix
//		System.out.println(autreChien.nom); --> Snoopy

		
		
//		Slide 97
		
//		Shape shape = new Shape();
//		shape.red = 100;
//		shape.green = 96;
//		shape.blue = 51;
//		shape.afficher();
//		ColorTool tool = new ColorTool();
//		tool.lighter(shape);
//		shape.afficher();
//		// shape.red : 115, shape.green : 111, shape.blue : 71

		
//		Slide 98
		
//		Chien monChien = new Chien(); 
//		monChien.poids = 15;
//		
//		Nourriture sonRepas = new Nourriture(); 
//		sonRepas.poids = 1;
//		// valeur de monChien.poids et sonRepas.poids ?
//		System.out.println(monChien.poids); // -->  15
//		System.out.println(sonRepas.poids); // -->  1
//		
//		monChien.nourrir(sonRepas);
//		// valeur de monChien.poids et sonRepas.poids ?
//		System.out.println(monChien.poids); // -->  16
//		System.out.println(sonRepas.poids); // -->  0
//		

//		Person p1 = new Person("Albert", 45, "Paris", "celibataire");
//		p1.situationChanger("en couple", "Marie");
//		p1.afficher();
//		p1.situationChanger("celibataire");
//		p1.afficher();
//		
//		Voiture v1= new Voiture("Fiesta", 5, 10000, 5);
//		v1.setModele("Citroen");
//		System.out.println(v1.modele);
		
//		Slide 115
		
//		Chien.ageMaxi = 20;
//		Chien monChien = new Chien();
//		// valeur de Chien.ageMaxi et monChien.ageMaxi ?
//		System.out.println(Chien.ageMaxi);   //  --> 20
//		System.out.println(monChien.ageMaxi); // --> 20
//		
//		monChien.ageMaxi = 15;
//		// valeur de Chien.ageMaxi et monChien.ageMaxi ?
//		System.out.println(Chien.ageMaxi);   // --> 15
//		System.out.println(monChien.ageMaxi);// --> 15
//		
//		
//		Chien autreChien = new Chien(); 
//		autreChien.ageMaxi = 12;
//		// valeur de Chien.ageMaxi, monChien.ageMaxi et autreChien.ageMaxi ?
//		System.out.println(Chien.ageMaxi);     // --> 12
//		System.out.println(monChien.ageMaxi);  // --> 12
//		System.out.println(autreChien.ageMaxi);// --> 12
		
	}
}
