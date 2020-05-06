package com.infotel.eshop.dao;

import com.infotel.eshop.model.ProductImage;

public interface ProductImageDao {

	ProductImage findById(int id); // doit renvoyer productimage et pas un proxy
}
