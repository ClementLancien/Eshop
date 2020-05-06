//
//public class Voiture {
//	String modele;
//	int puissance;
//	int price;
//	int position;
//	
//	Voiture(String modele, int puissance, int price, int position) {
//		this.modele = modele;
//		this.puissance = puissance;
//		this.price = price;
//		this.position=position;
//	}
//
//	public String getModele() {
//		return modele;
//	}
//
//	public void setModele(String modele) {
//		this.modele = modele;
//	}
//
//	public int getPuissance() {
//		return puissance;
//	}
//
//	public void setPuissance(int puissance) {
//		this.puissance = puissance;
//	}
//
//	public int getPrice() {
//		return price;
//	}
//
//	public void setPrice(int price) {
//		this.price = price;
//	}
//
//	void afficher() {
//		String res = "Le modele de la voiture est : " + this.modele + "\n" + "La puissance de la voiture est de : "
//				+ this.puissance + "\n" + "Le prix de cette voiture est de : " + this.price + "\u20ac";
//		System.out.println(res);
//	}
//
//	String avancer() {
//		return "La voiture avance";
//	}
//
//	void discount(int percent) {
//		if (percent < 0) {
//			return;
//		}
//		int newPrice = this.getPrice() - this.getPrice() * percent / 100;
//		this.setPrice(newPrice);
//	}
//	
//	
////	public static void main(String[] args) {
////		Voiture v1= new Voiture("Fiesta", 5, 10000, 5);
////		v1.setModele("Citroen");
////		System.out.println(v1.modele);
////	}
//	
//	
//	
//
//}
//}



public class Voiture {
    String modele;
    int puissance;
    static int nbVoitures;
    static final int PUISSANCE_MAX = 15;
    
//    public static void main(String[] args) {
//    	Voiture v1 = new Voiture();
//    	v1.modele = "Ford";
//    	v1.puissance = 5;
//    	v1.nbVoitures = 1;
//    	int j = Voiture.nbVoitures;
//    	System.out.println(j);
//    }
}


