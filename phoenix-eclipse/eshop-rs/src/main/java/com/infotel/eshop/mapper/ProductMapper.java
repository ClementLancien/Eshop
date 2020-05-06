package com.infotel.eshop.mapper;

import java.util.List;
import java.util.stream.Collectors;

import com.infotel.eshop.dto.FigureDto;
import com.infotel.eshop.dto.ProductFamilyDto;
import com.infotel.eshop.dto.ProductFullDto;
import com.infotel.eshop.dto.ProductLightDto;
import com.infotel.eshop.dto.ProductTagDto;
import com.infotel.eshop.dto.SuggestDto;
import com.infotel.eshop.model.Album;
import com.infotel.eshop.model.Book;
import com.infotel.eshop.model.Movie;
import com.infotel.eshop.model.Product;
import com.infotel.eshop.model.ProductImage;

public interface ProductMapper { // pour etre compatible avec des frameworkds de transformation tel que mapstruct

	static ProductFullDto productToProductFullDto(Product p) {
		ProductFullDto dto = new ProductFullDto();
		dto.setId(p.getId());
		dto.setName(p.getName());
		dto.setDescription(p.getDescription());
		dto.setPrice(p.getPrice());
		
		ProductFamilyDto famDto = ProductFamilyMapper.productFamilytoProductFamilyDto(p.getFamily());
		dto.setFamily(famDto);
		
		ProductImage image = p.getImage();
		if (image != null) {
			dto.setImageId(p.getImage().getId());
		} else {
			dto.setImageId(-1);
		}
		
		List<ProductTagDto> tagDtoes = p.getTags()
										.stream()
										.map(ProductTagMapper::productTagtoProductTagDto)
										.collect(Collectors.toList());
		
		dto.setTags(tagDtoes);
		
		if (p instanceof Book) {
			Book b = (Book) p;
			dto.setType("book");
			dto.setReleaseDate(b.getReleaseDate());
			dto.setIsbn(b.getIsbn());
			dto.setLanguage(b.getLanguage());
			dto.setPages(b.getPages());
			
			List<FigureDto> authorDtoes = b.getAuthors()
									       .stream()
									       .map(FigureMapper::figuretoFigureDto)
									       .collect(Collectors.toList());

			dto.setAuthors(authorDtoes);

		}
		else if (p instanceof Movie) {
			Movie m = (Movie) p;
			dto.setType("movie");
			dto.setReleaseDate(m.getReleaseDate());
			dto.setLengthMovie(m.getLength());
			dto.setLanguages(m.getLanguages());
			
			List<FigureDto> actorDtoes = m.getActors()
									 .stream()
								  	 .map(FigureMapper::figuretoFigureDto)
								  	 .collect(Collectors.toList());		
			dto.setActors(actorDtoes);
			
			dto.setDirector(FigureMapper.figuretoFigureDto(m.getDirector()));
		}
		else if (p instanceof Album) {
			Album a = (Album) p;
			dto.setType("album");
			dto.setReleaseDate(a.getReleaseDate());
			
			dto.setLengthAlbum(a.getLength());
			
			List<FigureDto> artistDtoes = a.getArtists()
									  .stream()
									  .map(FigureMapper::figuretoFigureDto)
									  .collect(Collectors.toList());
			dto.setArtists(artistDtoes);
		}
		return dto;
	}
	
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
