import java.io.*;

public class PairImpair {
	public static void main(String[] args) throws Exception {

		// definition des variables locales
		BufferedReader clavier = new BufferedReader(new InputStreamReader(System.in));
		System.out.print("Bonjour! Merci de saisir votre nombre : ");
		int value = Integer.parseInt(clavier.readLine());

		if (value == 0) {
			System.out.println("Le nombre est zéro (et il est pair)");
		} 
		else {
			if (value < 0) {
				System.out.print("Le nombre est négatif ");
			} else {
				System.out.print("Le nombre est négatif ");
			}

			int resDivision = value % 2;

			if (resDivision == 0) {
				System.out.println("et est pair.");
			} else {
				System.out.println("et est impair.");
			}

		}

	}

}
