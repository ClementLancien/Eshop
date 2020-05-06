package com.infotel.eshop.dao;

import java.util.Arrays;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import com.infotel.eshop.exception.EShopException;
import com.infotel.eshop.model.Order;
import com.infotel.eshop.model.OrderStatus;
import com.infotel.eshop.model.Product;

@Repository
public class OrderDaoJpa implements OrderDao {

	@PersistenceContext
	private EntityManager em;
	
	@Override
	public void create(Order order) throws EShopException {
		//EntityManager em = getEntityManager();
//		EntityTransaction tx = em.getTransaction();
//		tx.begin();
		
		// bug hibernate ?
		order.getLines().forEach(l -> {
			Product p = em.getReference(Product.class, l.getProduct().getId()); // plus interessant dutiliser getReference que getFind car ne lance pas les requetes sur la BDD
			l.setProduct(p);
		});
		
		em.persist(order);
		//tx.commit();
		//em.close();
			
	}

	@Override
	public List<Order> findByCustomer(String username) throws EShopException {
		//EntityManager em = getEntityManager();
		String jpql = "select distinct o from Order o join fetch o.lines where o.customer.username =:username";
		TypedQuery<Order> query = em.createQuery(jpql, Order.class)
									.setParameter("username", username);
		
		List<Order> orders = query.getResultList();
		// jpa ne charge pas association car LAZY car association OneToMany par defaut en LAZY
//		orders.forEach(o -> {
//			o.getLines().forEach(l -> {
//				Product p = em.getReference(Product.class, l.getProduct().getId()); // plus interessant dutiliser getReference que getFind car ne lance pas les requetes sur la BDD
//				l.setProduct(p);
//			});
//		});
		//em.close();
		return orders;
//		return orders.stream()
//					   .filter(o -> o.getCustomer().getUsername().equals(username))
//					   .collect(Collectors.toList());
	}

	@Override
	public List<Order> findWithStatus(OrderStatus... statusList) {
		String jpql = "select distinct o from Order o join fetch o.lines "
					+ "where o.status in :statusList";
		TypedQuery<Order> query = em.createQuery(jpql, Order.class)
									.setParameter("statusList", Arrays.asList(statusList));
		
		return query.getResultList();
	}

}
