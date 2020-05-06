package com.infotel.eshop.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.infotel.eshop.exception.EShopException;
import com.infotel.eshop.model.Product;

public class ProductDaoMemory implements ProductDao{
	
	private Product[] productTable;
	
	public ProductDaoMemory() {
		super();
		Product p1 = new Product();
		p1.setId(1);
		p1.setName("Astérix en Corse");
		p1.setPrice(12);
		
		Product p2 = new Product();
		p2.setId(2);
		p2.setName("Tintin au Tibet");
		p2.setPrice(11);
		
		Product p3 = new Product();
		p3.setId(3);
		p3.setName("Tintin en Amérique");
		p3.setPrice(12.5);
		
		productTable = new Product[] { p1, p2, p3 };
//		productTable.add(p1);
//		productTable.add(p2);
//		productTable.add(p3);
	}


	@Override
	public Map<String, Object> findByCriteria(String keyword, int familyId, int tagId, boolean randomize) {
	//public List<Product> findByCriteria(String keyword, int familyId, int tagId, boolean randomize) {
		
		List<Product> productArray = new ArrayList<>();
		for (Product product : productTable) {
			if(product.getName().toLowerCase().contains(keyword.toLowerCase())) {
				productArray.add(product);
			}
		}
		
		return null;
		//return productArray;
		
	}

	@Override
	public Product findById(int id) {
		for (Product product : productTable) {
			if (product.getId() == id) {
				return product;
			}
		}
		return null;
	}


	@Override
	public List<Product> findByTag(int id) throws EShopException {
		// TODO Auto-generated method stub
		return null;
	}


}