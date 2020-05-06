package com.infotel.eshop.search.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.infotel.eshop.search.dao.ProductDao;
import com.infotel.eshop.search.model.SuggestItem;

@Service 
public class CatalogServiceImpl implements CatalogService {
	
	private ProductDao productDaoEs;

	public CatalogServiceImpl(ProductDao productDaoEs) {
		super();
		this.productDaoEs = productDaoEs;
		
	}
	
	
	@Override
	public Map<String, Object> findProductByCriteria(String keyword, int familyId, String tag, boolean randomize) throws Exception {
		return productDaoEs.findByCriteria(keyword, familyId, tag, randomize);
	}



	@Override
	public List<SuggestItem> findSuggest(String keyword, int familyId) {
		return productDaoEs.findSuggest(keyword, familyId);
	}
	
	
}