package com.infotel.eshop.dao;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import com.infotel.eshop.model.ProductImage;

@Repository
public class ProductImageDaoJpa implements ProductImageDao {
	
	@PersistenceContext
	private EntityManager em;
	
	@Override
	public ProductImage findById(int id) {
		//EntityManager em = getEntityManager();
		
		try {
			return em.find(ProductImage.class, id);
		} catch(NoResultException e) {
			return null;
		} 
	}

}
