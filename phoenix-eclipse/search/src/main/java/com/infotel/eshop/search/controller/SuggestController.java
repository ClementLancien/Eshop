package com.infotel.eshop.search.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.infotel.eshop.search.dto.SuggestDto;
import com.infotel.eshop.search.mapper.SuggestMapper;
import com.infotel.eshop.search.model.SuggestItem;
import com.infotel.eshop.search.service.CatalogService;

@RestController @RequestMapping("/suggest")
public class SuggestController {

	@Autowired
	private CatalogService service;
	
	@GetMapping
	public List<SuggestDto> getSuggest(@RequestParam("q") String keyword, @RequestParam("id") int familyId) throws Exception { // "q" pour query
		//System.err.println("familyId : " + familyId);
		//List<Product> products = (List<Product>) service.findProductByCriteria(keyword, familyId, -1, false).get("products");
		List<SuggestItem> products = service.findSuggest(keyword, familyId);
		return products.stream()
					   .map(SuggestMapper::suggestItemToSuggestDto)
					   .collect(Collectors.toList());
	}
}
