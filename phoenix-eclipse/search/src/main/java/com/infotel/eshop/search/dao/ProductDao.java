package com.infotel.eshop.search.dao;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import com.infotel.eshop.search.model.Product;
import com.infotel.eshop.search.model.SuggestItem;

public interface ProductDao {
	
	Map<String, Object> findByCriteria(String keyword, int familyID, String tag, boolean randomize) throws Exception;
	
	List<Product> findByTag(int tagId) throws Exception;
	
	Product findById(int id) throws Exception;
	
	default List<SuggestItem> findSuggest(String keyword, int familyId) { // pr ne pas retourner un null
		return Collections.emptyList();
	}
}
