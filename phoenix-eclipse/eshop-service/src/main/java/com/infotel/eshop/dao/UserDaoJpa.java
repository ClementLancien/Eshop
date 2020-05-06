package com.infotel.eshop.dao;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import com.infotel.eshop.exception.EShopException;
import com.infotel.eshop.model.Customer;
import com.infotel.eshop.model.User;

@Repository
public class UserDaoJpa implements UserDao {
	
	@PersistenceContext
	private EntityManager em;
	
	@Override
	public User findByUsername(String username) throws EShopException {
		if(username == null) return null; // find ne peut pas prendre username egal a null
		
		//EntityManager em = getEntityManager();
		
		try {
			return em.find(User.class, username);
		} catch (NoResultException e) {
			return null;
		} 
		
	}

	@Override
	public void create(User user) throws EShopException {
		if(!(user instanceof Customer)) return;
		
		//EntityManager em = getEntityManager();
		//EntityTransaction tx = em.getTransaction();
		
		//Customer cust = (Customer)user;
		//tx.begin();
		em.persist(user);
//		if (cust.getDetail() != null) {
//			em.persist(cust.getDetail());
//		}
		//tx.commit();
		//em.close();
	}

}
