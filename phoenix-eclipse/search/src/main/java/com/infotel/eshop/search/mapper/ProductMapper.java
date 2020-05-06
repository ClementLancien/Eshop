package com.infotel.eshop.search.mapper;

import java.util.List;
import java.util.stream.Collectors;

import com.infotel.eshop.search.dto.ProductLightDto;
import com.infotel.eshop.search.dto.SuggestDto;
import com.infotel.eshop.search.model.Album;
import com.infotel.eshop.search.model.Book;
import com.infotel.eshop.search.model.Movie;
import com.infotel.eshop.search.model.Product;
import com.infotel.eshop.search.model.ProductImage;

public interface ProductMapper { // pour etre compatible avec des frameworkds de transformation tel que mapstruct

	
	static ProductLightDto productToProductLightDto(Product p) {
		ProductLightDto dto = new ProductLightDto();
		
		dto.setId(p.getId());
		dto.setName(p.getName());
		dto.setPrice(p.getPrice());
		dto.setDescription(p.getDescription());
		ProductImage image = p.getImage();
		if (image != null) {
			dto.setImageId(p.getImage().getId());
		} else {
			dto.setImageId(-1);
		}
		//dto.setImageId(p.getImage().getId());
		
		if (p instanceof Book) {
			dto.setType("book");
		}
		else if (p instanceof Movie) {
			dto.setType("movie");
		}
		else if (p instanceof Album) {
			dto.setType("album");
		}
		
		return dto;
	}
	
	static List<ProductLightDto> productToProductLightDtoes(List<Product> p) {
		return p.stream()
				 .map(ProductMapper::productToProductLightDto)
				 .collect(Collectors.toList());
	}
	
	static Product productIdToProduct(int productId) {
		Product prod = new Product();
		
		prod.setId(productId);
		
		return prod;
	}
	
	static SuggestDto productToSuggestDto(Product p) {
		SuggestDto dto = new SuggestDto();
		dto.setId(p.getId());
		dto.setName(p.getName());
		
		if (p instanceof Book) {
			dto.setType("book");
		}
		else if (p instanceof Movie) {
			dto.setType("movie");
		}
		else if (p instanceof Album) {
			dto.setType("album");
		}
		
		return dto;
	}
	
//	static List<SuggestDto> productsToSuggestDtoes(List<Product> p){
//		return p.stream()
//	}
	
}
