package com.infotel.eshop.ui;

import java.util.HashMap;
import java.util.Map;

public enum WindowFactory {
	Instance;
	
	private Map<String, Window> cache = new HashMap<>();
	
	public Window getWindow(String name) {
		Window window = cache.get(name);
		if (window != null) return window;
		
		switch (name) {
		case UiConstants.LOGIN:
			window = new LoginWindow();
			break;
			
		case UiConstants.SEARCH:
			window = new SearchWindow();
			break;
		
		case UiConstants.REGISTER:
			window = new RegisterWindow();
			break;
			
		case UiConstants.HOME:
			window = new HomeWindow();
			break;
		
		case UiConstants.DETAIL:
			window = new DetailWindow();
			break;
			
		case UiConstants.BASKET:
			window = new BasketWindow();
			break;
			
		case UiConstants.MY_ORDERS:
			window = new MyOrderWindow();
			break;
			
		case UiConstants.LANGUAGE:
			window = new LanguageWindow();
			break;
		}
		
		cache.put(name, window);
		
		return window;
	}
}
