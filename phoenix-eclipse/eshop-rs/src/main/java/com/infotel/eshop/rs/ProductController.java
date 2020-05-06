package com.infotel.eshop.rs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.infotel.eshop.dto.ProductFullDto;
import com.infotel.eshop.mapper.ProductMapper;
import com.infotel.eshop.model.Product;
import com.infotel.eshop.service.CatalogService;

@RestController @RequestMapping("/products")
public class ProductController {

	@Autowired
	private CatalogService service;
	
	@GetMapping("/{id}")
	public ProductFullDto getProduct(@PathVariable("id") int id ) throws Exception {
		Product p = service.findProductById(id);
		
		return ProductMapper.productToProductFullDto(p);
	}
	
}
