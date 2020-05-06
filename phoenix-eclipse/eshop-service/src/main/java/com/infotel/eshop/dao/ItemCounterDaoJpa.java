package com.infotel.eshop.dao;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import com.infotel.eshop.exception.EShopException;
import com.infotel.eshop.model.ItemCounter;

@Repository
public class ItemCounterDaoJpa implements ItemCounterDao  {

	@PersistenceContext
	private EntityManager em;
	
	@Override
	public void create(ItemCounter counter) throws EShopException {
		//EntityManager em = getEntityManager();
		//EntityTransaction tx = em.getTransaction();
		//tx.begin();
		em.persist(counter);
		//tx.commit();
		//em.close();
	}

	@Override
	public void update(ItemCounter counter) throws EShopException {
		//EntityManager em = getEntityManager();
//		EntityTransaction tx = em.getTransaction();
//		tx.begin();
		em.merge(counter);
//		tx.commit();
//		em.close();
	}

	@Override
	public ItemCounter findByID(String item, String subset) throws EShopException {
		//EntityManager em = getEntityManager();
		
		try {
			ItemCounter pk = new ItemCounter();
			pk.setItem(item);
			pk.setSubset(subset);
			return em.find(ItemCounter.class, pk); // pk car ItemCounter clé composé
		} catch (NoResultException e) {
			return null;
		} 
//		finally {
//			em.close();
//		}
		
//		String jpql = "select ic from ItemCounter ic";
//		TypedQuery<ItemCounter> query = em.createQuery(jpql, ItemCounter.class);
//		
//		
//		List<ItemCounter> counters = query.getResultList();
//		
//		em.close();
//		return counters.stream()
//					   .filter(ic -> ic.getItem().equals(item) && ic.getSubset().equals(subset))
//					   .findFirst() //permet de recuperer le 1er element du filtre si vide renvoit objet optionnal
//					   .orElse(null); // si dans la collection il ya un element tu renvoit le premier si tu renvois null
//					   //.get(); // retourne objet de type ItemCounter si pas optional sinn il plante
	}

}
