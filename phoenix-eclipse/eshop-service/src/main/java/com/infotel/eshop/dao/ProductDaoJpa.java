package com.infotel.eshop.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityGraph;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.infotel.eshop.exception.EShopException;
import com.infotel.eshop.model.Album;
import com.infotel.eshop.model.Book;
import com.infotel.eshop.model.Movie;
import com.infotel.eshop.model.Product;

@Repository @Qualifier("jpa")
public class ProductDaoJpa implements ProductDao {
	
	@PersistenceContext
	private EntityManager em;
	
	@Override
	public Map<String, Object> findByCriteria(String keyword, int familyId, int tagId, boolean randomize) throws EShopException {
	//public List<Product> findByCriteria(String keyword, int familyId, int tagId, boolean randomize) throws EShopException {
		//EntityManager em = getEntityManager();
		
		String jpql = "select p from Product p left join fetch p.tags t inner join fetch p.family where 1 = 1";
		if (keyword != null && !keyword.isBlank()) {
			jpql += " and p.name like :keyword";
		}
		
		if (familyId != -1) {
			jpql += " and p.family.id = :famId";
		}
		
		if (tagId != -1) {
			jpql += " and t.id = :tagId";
		}
		
		if (randomize) {
			jpql += " order by rand()";
		}
		
		TypedQuery<Product> query = em.createQuery(jpql, Product.class)
									  .setMaxResults(10);
		if (keyword != null && !keyword.isBlank()) {
			query.setParameter("keyword", "%" + keyword + "%");
		}
		
		if (familyId != -1) {
			query.setParameter("famId", familyId);
		}
		
		if (tagId != -1) {
			query.setParameter("tagId", tagId);
		}
		
		List<Product> products = query.getResultList();
		
		//em.close();
		
		return null;
		//return products;
		
	}

	@Override
	public Product findById(int id) throws EShopException {
		//EntityManager em = getEntityManager();
		
		EntityGraph<?> graph = em.createEntityGraph("productFull");//com.infotel.eshop.
		
		//tests
		//Subgraph<Book> itemGraph = graph.addSubgraph("book");
		//itemGraph.addAttributeNodes("authors");
		
		
		//em.createEntityGraph(Product.class).
		Map<String, Object> hints = new HashMap<>(); // hints pr indication
		hints.put("javax.persistence.loadgraph", graph); //fetchgrap
		
		try {
			//Product product = em.find(Product.class, id);
			Product product = em.find(Product.class, id, hints);
			
			//product.setPrice(99); update dans la base si on ajoute la transaction
			
			// car on veut tout affiche mais association many ne charge pas tt(par defaut LAZY)
			if (product instanceof Book) {
				Book book = (Book)product;
				book.getAuthors().size();
				//book.getTag().size();
			}
			else if (product instanceof Album) {
				Album album = (Album)product;
				album.getArtists().size();
				//album.getTags().size();
			}
			else if (product instanceof Movie) {
				Movie movie = (Movie)product;
				movie.getActors().size();
				movie.getLanguages().size();
				//movie.getTags().size();
			}
			return product;
		} catch (NoResultException e) {
			return null;
		} 
//		finally {
//			em.close();
//		}
		
	}

	@Override
	public List<Product> findByTag(int tagId) throws EShopException {
		//EntityManager em = getEntityManager();
		
			String jpql = "select p from Product p inner join fetch p.tags t where t.id = :tagId";
			

			
			TypedQuery<Product> query = em.createQuery(jpql, Product.class)
										  .setMaxResults(4);

			query.setParameter("tagId", tagId);

			System.err.println("++++++++" + query);
			List<Product> products = query.getResultList();
			
			//em.close();
			
			return products;
	}

}
