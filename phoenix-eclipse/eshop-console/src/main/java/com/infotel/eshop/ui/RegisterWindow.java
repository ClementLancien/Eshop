package com.infotel.eshop.ui;

import com.infotel.eshop.exception.EShopException;
import com.infotel.eshop.exception.RegisterException;
import com.infotel.eshop.model.Customer;
import com.infotel.eshop.service.UserService;

public class RegisterWindow extends Window {
	
	public RegisterWindow() {
		super("Inscription");
	}

	@Override
	protected void renderBody(Object... params) throws Exception {
		// saisie dans le formulaire
		String username = inputString("Identifiant : ");
		String password = inputString("Mot de passe: ");
		String firstName = inputString("Prénom: ");
		String lastName = inputString("Nom : ");
		
		// validation des saisies
		
		// invoquer le service métier
		Customer cust = new Customer(username, password, firstName, lastName);
		//UserService service = new UserServiceImpl();
		UserService service = getBean(UserService.class);
//		boolean result = service.registerCustomer(cust);
		
		// message après inscription
		render();
//		if (result) {
//			render("Vous êtes maintenant inscrit.");
//		} else {
//			render("Échec de l'inscription");
//		}
		try {
			service.registerCustomer(cust);
			render("Vous êtes maintenant inscrit.");
		} catch (EShopException | RegisterException e) {
			render("Échec de l'inscription");
		}
		render();
		
		// retour acceuil
		navigate(UiConstants.HOME);
	}
}
