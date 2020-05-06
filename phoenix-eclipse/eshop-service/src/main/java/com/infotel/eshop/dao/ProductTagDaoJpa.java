package com.infotel.eshop.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import com.infotel.eshop.exception.EShopException;
import com.infotel.eshop.model.ProductTag;

@Repository
public class ProductTagDaoJpa implements ProductTagDao {

	@PersistenceContext
	private EntityManager em;
	
	@Override
	public List<ProductTag> findAll() throws EShopException {
		String jpql = "select t from ProductTag t";
		TypedQuery<ProductTag> query = em.createQuery(jpql, ProductTag.class);
		
		
		List<ProductTag> tags = query.getResultList();
		
		for (ProductTag tag : tags) {
			System.err.println(tag.getName());
		}
		//em.close();
		
		return tags;
	}

}
