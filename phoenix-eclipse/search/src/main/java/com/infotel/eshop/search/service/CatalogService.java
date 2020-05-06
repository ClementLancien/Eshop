package com.infotel.eshop.search.service;

import java.util.List;
import java.util.Map;

import com.infotel.eshop.search.model.SuggestItem;

public interface CatalogService {

	List<SuggestItem> findSuggest(String keyword, int familyId);
	
	Map<String, Object> findProductByCriteria(String keyword, int familyId, String tag, boolean randomize) throws Exception;

}