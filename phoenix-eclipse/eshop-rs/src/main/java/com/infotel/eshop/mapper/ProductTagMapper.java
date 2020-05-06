package com.infotel.eshop.mapper;

import java.util.List;
import java.util.stream.Collectors;

import com.infotel.eshop.dto.ProductTagDto;
import com.infotel.eshop.model.ProductTag;

public interface ProductTagMapper {

	static ProductTagDto productTagtoProductTagDto(ProductTag tag) {
		ProductTagDto dto = new ProductTagDto();
		dto.setId(tag.getId());
		dto.setName(tag.getName());
		System.err.println(dto);
		return dto;
	}
	
	static List<ProductTagDto> listProductTagToProductTagDtoes(List<ProductTag> tags) {
		return tags.stream()
				  .map(ProductTagMapper::productTagtoProductTagDto)
				  .collect(Collectors.toList());
	}
}
