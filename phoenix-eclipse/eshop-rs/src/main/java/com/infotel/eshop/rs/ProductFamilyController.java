package com.infotel.eshop.rs;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.infotel.eshop.dto.ProductFamilyDto;
import com.infotel.eshop.dto.ProductFamilyListDto;
import com.infotel.eshop.mapper.ProductFamilyMapper;
import com.infotel.eshop.service.CatalogService;

@RestController @RequestMapping("/families")
public class ProductFamilyController {
	
	@Autowired
	public CatalogService service;
	
	@GetMapping(produces = MediaType.APPLICATION_XML_VALUE)
	public ProductFamilyListDto getFamiliesXml() throws Exception { // une liste ne fonctionne pas car il faut un element racine
		ProductFamilyListDto dto = new ProductFamilyListDto();
		
		List<ProductFamilyDto> familyDtoes = service.findAllFamilies()
													.stream()
													.map(ProductFamilyMapper::productFamilytoProductFamilyDto)
													.collect(Collectors.toList());
		dto.setFamilies(familyDtoes);
		return dto;
	}
	
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public List<ProductFamilyDto> getFamiliesJson() throws Exception {
		return service.findAllFamilies()
					  .stream()
					  .map(ProductFamilyMapper::productFamilytoProductFamilyDto)
					  .collect(Collectors.toList());
	}
}
