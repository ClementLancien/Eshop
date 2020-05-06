package com.infotel.eshop.search.dao;

import com.infotel.eshop.search.model.ProductImage;

public interface ProductImageDao {

	ProductImage findById(int id); // doit renvoyer productimage et pas un proxy
}
