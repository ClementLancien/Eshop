package com.infotel.eshop.search.controller;

import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.infotel.eshop.search.dto.ResultProductLightDto;
import com.infotel.eshop.search.dto.SearchDto;
import com.infotel.eshop.search.dto.SearchProductLightDto;
import com.infotel.eshop.search.mapper.ProductMapper;
import com.infotel.eshop.search.model.Product;
import com.infotel.eshop.search.service.CatalogService;

@RestController @RequestMapping("/search")
public class SearchController {

	private final static Logger log = LogManager.getLogger(SearchController.class);
	
	@Autowired
	private CatalogService service;
	
	@PostMapping(consumes= {MediaType.APPLICATION_JSON_VALUE,
						    MediaType.APPLICATION_XML_VALUE},
				 produces = MediaType.APPLICATION_JSON_VALUE)
	public ResultProductLightDto getSearchResultJson(@RequestBody SearchDto dto) throws Exception {
		Map<String, Object> resultat = service.findProductByCriteria(dto.getKeyword(), dto.getFamilyId(), dto.getTag(), dto.isRandomize());
		//List<Product> products = service.findProductByCriteria(dto.getKeyword(), dto.getFamilyId(), dto.getTagId(), dto.isRandomize());
		//System.err.println("Hereeeeee");
		
		if (log.isDebugEnabled()) {
			log.debug("search dto : " + dto);
		}
		
		List<Product> products = (List<Product>) resultat.get("products");
		int sizeCollection = (Integer) resultat.get("sizeCollection"); 
		
		ResultProductLightDto resultDtoes = new ResultProductLightDto();
		resultDtoes.setSizeCollection(sizeCollection);
		resultDtoes.setProducts(ProductMapper.productToProductLightDtoes(products));
		//return ProductMapper.productToProductLightDtoes(products);
		return resultDtoes;
	}
}
