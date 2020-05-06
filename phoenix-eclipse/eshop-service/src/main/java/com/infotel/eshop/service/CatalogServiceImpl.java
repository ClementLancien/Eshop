package com.infotel.eshop.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.infotel.eshop.dao.ProductDao;
import com.infotel.eshop.dao.ProductFamilyDao;
import com.infotel.eshop.dao.ProductImageDao;
import com.infotel.eshop.dao.ProductTagDao;
import com.infotel.eshop.exception.EShopException;
import com.infotel.eshop.model.Product;
import com.infotel.eshop.model.ProductFamily;
import com.infotel.eshop.model.ProductImage;
import com.infotel.eshop.model.ProductTag;
import com.infotel.eshop.model.SuggestItem;

@Service @Transactional
public class CatalogServiceImpl implements CatalogService {
	//pour spring permettre de simplifier la lecture grace aux champs dinstance
	//@Autowired
	private ProductDao productDao;
	private ProductDao productDaoEs;
	//private ProductDao productDao = new ProductDaoJpa();
	//@Autowired
	private ProductFamilyDao productFamilyDao;
	//private ProductFamilyDao productFamilyDao = new ProductFamilyDaoJpa();
	//@Autowired
	private ProductImageDao productImageDao;
	//private ProductImageDao productImageDao = new ProductImageDaoJpa();
	
	private ProductTagDao productTagDao;
	
	//@Autowired
	public CatalogServiceImpl(@Qualifier("jpa")ProductDao productDao,
							  @Qualifier("es")ProductDao productDaoEs, ProductFamilyDao productFamilyDao,
							  ProductTagDao productTagDao,
			ProductImageDao productImageDao) {
		super();
		this.productDao = productDao;
		this.productDaoEs = productDaoEs;
		this.productFamilyDao = productFamilyDao;
		this.productImageDao = productImageDao;
		this.productTagDao = productTagDao;
	}
	
	
	@Override
	public Map<String, Object> findProductByCriteria(String keyword, int familyId, String tag, boolean randomize) throws EShopException {
		//on déclare abstrait et on initialise concret
		
//		ProductDao productDao = new ProductDaoMemory();
//		ProductDao productDao = new ProductDaoFile();
		
//		try {
//			Thread.sleep(2000);
//		} catch (InterruptedException e) {}
//		
		//ProductDao productDao = new ProductDaoJdbc();
		//return productDao.findByCriteria(keyword, familyId, tagId, randomize);
		return productDaoEs.findByCriteria(keyword, familyId, tag, randomize);
	}


	@Override
	public Product findProductById(int id) throws EShopException {
//		ProductDao productDao = new ProductDaoMemory();
//		ProductDao productDao = new ProductDaoFile();
		//ProductDao productDao = new ProductDaoJdbc();
		//ProductDao productDao = new ProductDaoJpa();
		return productDao.findById(id);
//		return null;
	}

	@Override
	public List<ProductFamily> findAllFamilies() throws EShopException {
		//ProductFamilyDao productFamilyDao = new ProductFamilyDaoJdbc();
		return productFamilyDao.findAll();
	}

	@Override
	public ProductImage findProductImageById(int id) {
		return productImageDao.findById(id);
	}


	@Override
	public List<Product> findProductByTag(int tagId) throws EShopException {
		return productDao.findByTag(tagId);
	}


	@Override
	public List<SuggestItem> findSuggest(String keyword, int familyId) {
		return productDaoEs.findSuggest(keyword, familyId);
	}


	@Override
	public List<ProductTag> findAllTags() throws EShopException {
		return productTagDao.findAll();
	}
	
	
}