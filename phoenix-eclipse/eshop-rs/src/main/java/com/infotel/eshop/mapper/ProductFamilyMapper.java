package com.infotel.eshop.mapper;

import com.infotel.eshop.dto.ProductFamilyDto;
import com.infotel.eshop.model.ProductFamily;

public interface ProductFamilyMapper {

	static ProductFamilyDto productFamilytoProductFamilyDto(ProductFamily family) {
		ProductFamilyDto dto = new ProductFamilyDto();
		dto.setId(family.getId());
		dto.setName(family.getName());
		
		return dto;
	}
}
