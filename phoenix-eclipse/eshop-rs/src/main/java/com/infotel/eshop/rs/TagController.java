package com.infotel.eshop.rs;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.infotel.eshop.dto.ProductTagDto;
import com.infotel.eshop.mapper.ProductTagMapper;
import com.infotel.eshop.service.CatalogService;

@RestController @RequestMapping("/tags")
public class TagController {

	@Autowired
	private CatalogService service;
	
	@GetMapping
	public List<ProductTagDto> getTags() throws Exception {
		return service.findAllTags()
					  .stream()
					  .map(ProductTagMapper::productTagtoProductTagDto)
					  .collect(Collectors.toList());
					
	}
}
