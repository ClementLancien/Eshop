package com.infotel.eshop.ui;

//public class Router {

public enum Router {
	
	Instance;
	
	public void navigate(String target, Object... params) {
		Window window = WindowFactory.Instance.getWindow(target);
		window.display(params); // cest mieux denvoyer au display qu'a la factory
	}
	
}