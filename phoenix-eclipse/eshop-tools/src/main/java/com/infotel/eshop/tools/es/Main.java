package com.infotel.eshop.tools.es;

public class Main {
	
	public static void main(String[] args) throws Exception {
		importData();
	}

	public static void importData() throws Exception {
		CatalogImporter importer = new CatalogImporter();
		importer.importData();
	}

}
