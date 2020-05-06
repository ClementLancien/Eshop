import java.util.Random;
import java.io.*;

/**
 * Ce programme fait deviner a l'utilisateur un nombre entier secret compris entre 0 et 99. 
 * L'utilisateur a 6 essais, au maximum, pour d�couvrir le nombre, et est informe apres chaque 
 * tentative si le nombre est plus grand, plus petit ou egal au nombre cherche. 
 */
public class Devinette {

	/**
	 * Point d'entree.
	 * Aucun parametre.
	 * @throws  
     */
	public static void main(String[] args){
		try {
			new Devinette().start();
		} catch (NbEssaiDepasseExeption e) {
			System.out.println(e.getMessage());
		}
	}
	
	public void start() throws NbEssaiDepasseExeption {

		// definition des variables locales
		BufferedReader clavier =
			new BufferedReader(new InputStreamReader(System.in));
		int nombreSecret;

		// generation du nombre aleatoire
		Random aleatoire = new Random();
		nombreSecret = aleatoire.nextInt() % 100; // -99 et 99
		if (nombreSecret < 0) { // pour mettre en positif
			nombreSecret = -nombreSecret;
		}
		// nombre secret entre 0 et 99

		int nbEssai = 1;
//		int valeur;
		boolean trouve = false;
		int value = 0;
		
		while (!trouve) {
			System.out.print("Votre nombre : ");
			
					
			try {
				value = Integer.parseInt(clavier.readLine());
			} catch (NumberFormatException e) {
				System.out.println("Vous devez saisir un nombre et pas de texte!");
				
			} catch (IOException e) {
				e.printStackTrace();
			}
		
//			System.out.println("Vous devez saisir un nombre et pas de texte!");
//			System.out.println("Votre nombre : ");
//			value = Integer.parseInt(clavier.readLine());
			
			
//			boolean error=false;
//			do {
//				try {
//					value = Integer.parseInt(clavier.readLine());
//					error=false;
//				} catch (NumberFormatException nfe) {
//					System.out.println("Vous devez saisir un nombre et pas de texte!");
//					System.out.println("Votre nombre : ");
//					value = Integer.parseInt(clavier.readLine());
//					error=true;
//				}
//			}while(error);
			
			
//			int value = Integer.parseInt(clavier.readLine());
			if (nombreSecret > value) {
				System.out.println("Le nombre est plus grand ");
			}
			else if(nombreSecret < value) {
				System.out.println("Le nombre est plus petit");
			}
			else {
				System.out.println("Nombre trouvé. Bravo !");
				trouve = true;
				break;
			}
			
			nbEssai++;
			
			if (nbEssai > 6 ) {
				throw new NbEssaiDepasseExeption("Perdu. Le nombre secret etait " + nombreSecret);
//				System.out.println("Perdu. Le nombre secret etait " + nombreSecret);
//				break;
			}
		}
		
		// Faire deviner le nombre en moins de 7 tentatives
		/* Aide :
			1. On lit une entrée clavier en appelant Integer.parseInt(clavier.readLine());
			   Exemple : valeur = Integer.parseInt(clavier.readLine());
			2. On écrit dans la console en appelant System.out.println(...);
			   Exemple : System.out.println("Nombre trop grand");
		*/
		
	   
		// COMPLETER LE CODE
		
//		for (int i = 0; i < 6; i++) {
//			System.out.print("Votre nombre : ");
//			int value;
//			
//			try {
//				value = Integer.parseInt(clavier.readLine());
//			} catch (NumberFormatException nfe) {
//				System.out.println("Vous devez saisir un nombre et pas de texte!");
//				System.out.println("Votre nombre : ");
//				value = Integer.parseInt(clavier.readLine());
//			}
//			
////			boolean error=false;
////			do {
////				try {
////					value = Integer.parseInt(clavier.readLine());
////					error=false;
////				} catch (NumberFormatException nfe) {
////					System.out.println("Vous devez saisir un nombre et pas de texte!");
////					System.out.println("Votre nombre : ");
////					value = Integer.parseInt(clavier.readLine());
////					error=true;
////				}
////			}while(error);
//			
//			
////			int value = Integer.parseInt(clavier.readLine());
//			if (nombreSecret > value) {
//				System.out.println("Le nombre est plus grand ");
//			}
//			else if(nombreSecret < value) {
//				System.out.println("Le nombre est plus petit");
//			}
//			else {
//				trouve = true;
//				break;
//			}
//			nbEssai++;
//		}
//
//		if (trouve) {
//			System.out.println("Bravo! Le nombre secret etait " + nombreSecret);
//			System.out.println("Vous l'avez trouve au bout de " + nbEssai + " essais");
//		} else {
//			System.out.println("Perdu. Le nombre secret etait " + nombreSecret);
//		}
		
		
//		while (trouve != false || nbEssai < 7){
//			
//			System.out.print("Votre nombre : ");
//			valeur = Integer.parseInt(clavier.readLine());
//			
//			if(nombreSecret == valeur){
//				trouve = true;
////				System.out.println("Bravo! Le nombre secret etait " + nombreSecret);
////				System.out.println("Vous l'avez trouve au bout de " + nbEssai + " essais");
//			}
////			else if (nombreSecret != valeur && nbEssai == 6) {
////				break;
////			}
//			else if (nombreSecret > valeur) {
//				System.out.println("Le nombre est plus grand ");
////				System.out.println("Il vous reste");
//			}
//			else if(nombreSecret < valeur) {
//				System.out.println("Le nombre est plus petit");
//			}
//			else {
//				break;
//			}
//			nbEssai++;
//		}
//		if(trouve == true) {
//			System.out.println("Bravo! Le nombre secret etait " + nombreSecret);
//		}
//		else {
//			System.out.println("Perdu. Le nombre secret etait " + nombreSecret);
//		}
//		do {
//			
//			int value = Integer.parseInt(clavier.readLine());
//			System.out.print("Veuillez saisir votre nombre : ");
//			int essaiRestant = 6-nbEssai;
//			if (nombreSecret > value) {
//				System.out.println("Le nombre est plus grand!! Il vous reste" + essaiRestant);
//			}
//			else if(nombreSecret < value){
//				System.out.println("Le nombre est plus petit!! Il vous reste" + essaiRestant);
//			}
//				
//			
//		} while (trouve == false && nbEssai < 7);
		
	}
}