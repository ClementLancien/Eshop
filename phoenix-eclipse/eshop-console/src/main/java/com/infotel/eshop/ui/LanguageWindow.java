package com.infotel.eshop.ui;

public class LanguageWindow extends Window {

	public LanguageWindow() {
		super("lang.title");
	}

	@Override
	protected void renderBody(Object... params) throws Exception {
		
		render("1. " + getMessage("lang.menu.fr"));
		render("2. " + getMessage("lang.menu.en"));
		
		String lang = inputString(getMessage("lang.input.select.lang"));
		
		switch (lang) {
		case "1":
			SessionContext.Instance.changeLanguage("fr", "Fr");
			break;
			
		case "2":
			SessionContext.Instance.changeLanguage("en", "US");
			break;
		}
		navigate(UiConstants.HOME);
		
	}

}
