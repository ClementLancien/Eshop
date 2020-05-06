package com.infotel.eshop.service;

import java.util.List;
import java.util.Map;

import com.infotel.eshop.exception.EShopException;
import com.infotel.eshop.model.Product;
import com.infotel.eshop.model.ProductFamily;
import com.infotel.eshop.model.ProductImage;
import com.infotel.eshop.model.ProductTag;
import com.infotel.eshop.model.SuggestItem;

public interface CatalogService {

	List<SuggestItem> findSuggest(String keyword, int familyId);
	
	Map<String, Object> findProductByCriteria(String keyword, int familyId, String tag, boolean randomize) throws EShopException;

	Product findProductById(int id) throws EShopException;
	
	List<ProductTag> findAllTags() throws EShopException;
	
	List<Product> findProductByTag(int id) throws EShopException;
	
	List<ProductFamily> findAllFamilies() throws EShopException;
	
	ProductImage findProductImageById(int id);
}