package com.infotel.eshop.fx;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Main {
	private final static Logger log = LogManager.getLogger(EShopApp.class);
	
	public static void main(String[] args) {
		log.info("L'application démarre");
		EShopApp.launch(EShopApp.class);
		log.info("L'application s'arrête");
	}
}
