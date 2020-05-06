package com.infotel.eshop.search.dao;

import java.util.List;

import com.infotel.eshop.search.model.ProductFamily;

public interface ProductFamilyDao {
	List<ProductFamily> findAll() throws Exception;
}
