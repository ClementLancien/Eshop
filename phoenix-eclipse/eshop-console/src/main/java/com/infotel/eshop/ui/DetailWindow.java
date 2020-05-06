package com.infotel.eshop.ui;

import java.time.format.DateTimeFormatter;

import com.infotel.eshop.model.Album;
import com.infotel.eshop.model.Book;
import com.infotel.eshop.model.Movie;
import com.infotel.eshop.model.Product;
import com.infotel.eshop.service.CatalogService;

public class DetailWindow extends Window {
	
	public DetailWindow() {
		super("detail.title");
	}

	@Override
	protected void renderBody(Object... params) throws Exception {
		int productId = (int)params[0];
		
		//CatalogService service = new CatalogServiceImpl()CatalogService service = new CatalogServiceImpl();
		CatalogService service = getBean(CatalogService.class);
		Product product = service.findProductById(productId);		
		DateTimeFormatter dateFmt = DateTimeFormatter.ofPattern("dd/MM/yyyy");

		// on affiche
//		render("Titre : " + product.getName());
//		render("Prix : " + product.getPrice() + " €");
//		render("Publication : " + product.getReleaseDate().format(dateFmt));
		render(getMessage("detail.render.title", product.getName()));
		render(getMessage("detail.render.title", product.getPrice()));
		if (product instanceof Book) {
			Book book = (Book) product;
			render(getMessage("detail.render.date", book.getReleaseDate().format(dateFmt)));
		}
		if (product instanceof Album) {
			Album album = (Album) product;
			render(getMessage("detail.render.date", album.getReleaseDate().format(dateFmt)));
		}
		
		if (product instanceof Movie) {
			Movie movie = (Movie) product;
			render(getMessage("detail.render.date", movie.getReleaseDate().format(dateFmt)));
		}
		
		// menu
		render();
		render("1. " + getMessage("detail.menu.addToBasket")); // on demande à l'utilisateur de saisir une quantité
		render("2. " + getMessage("detail.menu.goBack"));
		render();
		
		String choice = inputString(getMessage("detail.choice"));
		switch (choice) {
		case "1":
			addToBasket(product);
			break ;
		case "2":
			navigate(UiConstants.SEARCH); // c'est intéressant de revenir vers search
			break ;
		
		}
	}
	
	private void addToBasket(Product product) {
		int quantity = inputInt(getMessage("detail.quantity"));
		//ajouter au panier
		SessionContext.Instance.addProductToBasket(product, quantity);
		
		navigate(UiConstants.HOME);
	}
}
