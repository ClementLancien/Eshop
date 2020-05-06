package com.infotel.eshop.dao;

import java.util.List;

import com.infotel.eshop.exception.EShopException;
import com.infotel.eshop.model.ProductFamily;

public interface ProductFamilyDao {
	List<ProductFamily> findAll() throws EShopException;
}
