package com.infotel.eshop.ui;

/**
 * C'est la classe pour la <strong>page d'accueil</strong> de l'application
 * @author c_lancien
 * @since v1.0
 */
public class HomeWindow extends Window {
	
	/**
	 * Le titre de la page d'accueil
	 */
//	private final static String title = ResourceBundle.getBundle("com.infotel.eshop.ui.i18n.Translate", new Locale("fr", "FR")).getString("login.username");;
	
	//static ResourceBundle bundle = ResourceBundle.getBundle("com.infotel.eshop.ui.i18n.Translate");
	
	public HomeWindow() {
		super("home.title");
	}

	/**
	 * Le rendu de la page d'accueil
	 * @param params Une liste des paramètres reçus par la page
	 */
	@Override
	protected void renderBody(Object... params) {
		
		render("1. " + getMessage("home.menu.search")); //Rechercher");
		render("2. " + getMessage("home.menu.login"));//Authentification");
		render("3. " + getMessage("home.menu.register"));//Inscription");
		render("4. " + getMessage("home.menu.basket"));//Voir le Panier");
		render("5. " + getMessage("home.menu.orders"));//Mes commandes");
		render("8. " + getMessage("home.menu.lang"));//Mes commandes");
		render("9. " + getMessage("home.menu.quit"));//Quitter");
		
		String choix = inputString("");
		
		switch (choix) {
		case "1":
			// Window searchWindow (déclarer abstrait) = partie on instancie
			navigate(UiConstants.SEARCH);
			break;
			
		case "2":
			navigate(UiConstants.LOGIN);
			break;
			
		case "3":
			navigate(UiConstants.REGISTER);
			break;
			
		case "4":
			navigate(UiConstants.BASKET);
			break;
			
		case "5":
			if(SessionContext.Instance.getCustomer() == null) {
				render();
				render(getMessage("home.message.loginRequired"));
				navigate(UiConstants.LOGIN, UiConstants.MY_ORDERS);
			} else {
				navigate(UiConstants.MY_ORDERS);
			}
			break;
		
		case "8":
			//bundle = ResourceBundle.getBundle("com.infotel.eshop.ui.i18n.Translate", Locale.US);
			navigate(UiConstants.LANGUAGE);
			break;
			
		case "9":
			render();
			render("Bye !");
			//System.exit(0);
			break;
		}
	}
}
