package com.infotel.eshop.rs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.infotel.eshop.model.ProductImage;
import com.infotel.eshop.service.CatalogService;

@RestController @RequestMapping("/images")
public class ImageController {

	@Autowired
	private CatalogService service;
	
	@GetMapping
	public ResponseEntity<byte[]> getImage(@RequestParam("id") int id) {
		
		ProductImage image = service.findProductImageById(id);
		if (image == null) {
			return new ResponseEntity<byte[]>(HttpStatus.NOT_FOUND); // erreur code 404
		}
		
		byte[] content = image.getContent();
		
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.IMAGE_PNG);
		headers.setContentLength(content.length);
		
		return new ResponseEntity<byte[]>(content, headers, HttpStatus.OK);
		
	}
	
//	@PostMapping("/{id}")
//	public ResponseEntity<byte[]> getImage(@PathVariable("id") int id) {
//		
//		ProductImage image = service.findProductImageById(id);
//		if (image == null) {
//			return new ResponseEntity<byte[]>(HttpStatus.NOT_FOUND); // erreur code 404
//		}
//		
//		byte[] content = image.getContent();
//		
//		HttpHeaders headers = new HttpHeaders();
//		headers.setContentType(MediaType.IMAGE_PNG);
//		headers.setContentLength(content.length);
//		
//		return new ResponseEntity<byte[]>(content, headers, HttpStatus.OK);
//		
//	}
	
}
