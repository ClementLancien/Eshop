package com.infotel.eshop.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import com.infotel.eshop.exception.EShopException;
import com.infotel.eshop.model.ProductFamily;

@Repository
public class ProductFamilyDaoJpa implements ProductFamilyDao {
	
	@PersistenceContext
	private EntityManager em;
	
	@Override
	public List<ProductFamily> findAll() throws EShopException {
		//EntityManager em = getEntityManager();
		
		String jpql = "select f from ProductFamily f";
		TypedQuery<ProductFamily> query = em.createQuery(jpql, ProductFamily.class);
		
		
		List<ProductFamily> families = query.getResultList();
		
		//em.close();
		
		return families;
	}

}
