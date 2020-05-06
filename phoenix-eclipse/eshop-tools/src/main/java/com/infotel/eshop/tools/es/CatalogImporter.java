package com.infotel.eshop.tools.es;

import java.util.List;

import com.infotel.eshop.tools.es.model.Product;
import com.infotel.eshop.tools.es.model.ProductFamily;
import com.infotel.eshop.tools.es.model.ProductImage;

public class CatalogImporter {

	public void importData() throws Exception {
		try (CatalogJdbcReader reader = new CatalogJdbcReader();
			CatalogIndexWriter writer = new CatalogIndexWriter();) {

			// families
			List<ProductFamily> families = reader.findFamilies();
			writer.indexFamilies(families);

			// products
			List<Product> products = reader.findProducts();
			writer.indexProducts(products);

			// images
			List<ProductImage> images = reader.findImages();
			writer.indexImages(images);
		}
	}
	
}
