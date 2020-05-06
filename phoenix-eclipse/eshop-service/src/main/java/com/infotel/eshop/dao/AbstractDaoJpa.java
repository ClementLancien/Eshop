package com.infotel.eshop.dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

@Deprecated
public class AbstractDaoJpa {

	private static EntityManagerFactory emf = Persistence.createEntityManagerFactory("eshop");

	protected EntityManager getEntityManager() {
		EntityManager em = emf.createEntityManager();
		return em;
	}
}