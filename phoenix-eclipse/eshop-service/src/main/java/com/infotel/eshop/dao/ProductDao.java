package com.infotel.eshop.dao;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import com.infotel.eshop.exception.EShopException;
import com.infotel.eshop.model.Product;
import com.infotel.eshop.model.SuggestItem;

public interface ProductDao {
	
	Map<String, Object> findByCriteria(String keyword, int familyID, String tag, boolean randomize) throws EShopException;
	
	List<Product> findByTag(int tagId) throws EShopException;
	
	Product findById(int id) throws EShopException;
	
	default List<SuggestItem> findSuggest(String keyword, int familyId) { // pr ne pas retourner un null
		return Collections.emptyList();
	}
}
