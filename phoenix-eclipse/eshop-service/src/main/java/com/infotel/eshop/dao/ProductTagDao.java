package com.infotel.eshop.dao;

import java.util.List;

import com.infotel.eshop.exception.EShopException;
import com.infotel.eshop.model.ProductTag;

public interface ProductTagDao {

	List<ProductTag> findAll() throws EShopException;
	
}
