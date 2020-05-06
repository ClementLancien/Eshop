package com.infotel.eshop.ui;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class EShopApp {
	
	private final static Logger log = LogManager.getLogger(EShopApp.class);
	
	public static void main(String[] args) {
		
////		Couche ecran (presentation)
//		System.out.println("Votre recherche : ");
//		
//		Scanner scanner = new Scanner(System.in);
//		String keyword = scanner.nextLine();
//		scanner.close();
		
////		System.out.println("Saisie : " + keyword);
//		
//		
////		code transitoire pour initialiser le tableau
////		Couche bdd DAO
//		Product p1 = new Product();
//		p1.setId(1);
//		p1.setName("Astérix en Corse");
//		
//		Product p2 = new Product();
//		p2.setId(2);
//		p2.setName("Tintin au Tibet");
//		
//		Product p3 = new Product();
//		p3.setId(3);
//		p3.setName("Tintin en Amérique");
//		
//
//		Product[] database = { p1, p2, p3 };
////		fin
//		
////		DAO
//		for (Product product : database) {
//			if(product.getName().toLowerCase().contains(keyword.toLowerCase())) {
//				System.out.println(product.getName());
//			}
//		}
		
////		service metier mais appelle au niveau de l'écran
//		CatalogService service = new CatalogService();
//		Product[] products = service.findProductByCriteria(keyword);
//		
////		ecran
//		for (Product product : products) {
//			System.out.println(product.getName());
//		}
		
//		SearchWindow searchWindow = new SearchWindow();
//		searchWindow.display();
		
//		LoginWindow loginWindow = new LoginWindow();
//		loginWindow.display();

//		Window window = new WindowFactory().getWindow("home");
//		window.display();
//		new Router().navigate("home");
//		Router.getInstance().navigate(UiConstants.HOME);
		
		log.info("L'application démarre");
		
		SessionContext.Instance.init();
		
		Router.Instance.navigate(UiConstants.HOME);
		
		SessionContext.Instance.shutdown();
		
		log.info("L'application s'arrête");
	}
}