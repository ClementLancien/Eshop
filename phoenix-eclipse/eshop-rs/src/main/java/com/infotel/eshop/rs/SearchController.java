package com.infotel.eshop.rs;

import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.infotel.eshop.dto.SearchDto;

import okhttp3.HttpUrl;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

@RestController @RequestMapping("/search")
public class SearchController {
/*********************************************OLD**********************************************/
//	private final static Logger log = LogManager.getLogger(SearchController.class);
//	
//	@Autowired
//	private CatalogService service;
//	
//	@PostMapping(consumes= {MediaType.APPLICATION_JSON_VALUE,
//							MediaType.APPLICATION_XML_VALUE},
//			     produces = MediaType.APPLICATION_XML_VALUE)
//	public SearchProductLightDto getSearchResultXml(@RequestBody SearchDto dto) throws Exception {
//		List<Product> products = (List<Product>) service.findProductByCriteria(dto.getKeyword(), dto.getFamilyId(), dto.getTagId(), dto.isRandomize()).get("products");
//		SearchProductLightDto pdto = new SearchProductLightDto();
//		pdto.setProducts(ProductMapper.productToProductLightDtoes(products));
//		return pdto;
//	}
//	
//	@PostMapping(consumes= {MediaType.APPLICATION_JSON_VALUE,
//						    MediaType.APPLICATION_XML_VALUE},
//				 produces = MediaType.APPLICATION_JSON_VALUE)
//	public ResultProductLightDto getSearchResultJson(@RequestBody SearchDto dto) throws Exception {
//		Map<String, Object> resultat = service.findProductByCriteria(dto.getKeyword(), dto.getFamilyId(), dto.getTagId(), dto.isRandomize());
//		//List<Product> products = service.findProductByCriteria(dto.getKeyword(), dto.getFamilyId(), dto.getTagId(), dto.isRandomize());
//		//System.err.println("Hereeeeee");
//		
//		if (log.isDebugEnabled()) {
//			log.debug("search dto : " + dto);
//		}
//		
//		List<Product> products = (List<Product>) resultat.get("products");
//		int sizeCollection = (Integer) resultat.get("sizeCollection"); 
//		
//		ResultProductLightDto resultDtoes = new ResultProductLightDto();
//		resultDtoes.setSizeCollection(sizeCollection);
//		resultDtoes.setProducts(ProductMapper.productToProductLightDtoes(products));
//		//return ProductMapper.productToProductLightDtoes(products);
//		return resultDtoes;
//	}
	
/*************************************MS****************************************************/

	private final static Logger log = LogManager.getLogger(SearchController.class);
	
	@Autowired
	private OkHttpClient client;
	
	@PostMapping
	public ResponseEntity<String> getSearchResult(@RequestBody SearchDto dto) throws Exception{
		HttpUrl.Builder urlBuilder = HttpUrl.parse("http://localhost:8082" + "/search").newBuilder();
		String url = urlBuilder.build().toString();

		
		ObjectMapper mapper = new ObjectMapper();
		
		try {
			okhttp3.RequestBody body = okhttp3.RequestBody.create(mapper.writeValueAsString(dto), MediaType.get("application/json"));
			Request request = new Request.Builder()
										 .url(url)
										 .post(body)
										 .build();
			
			Response response = client.newCall(request).execute();
			return new ResponseEntity<String>(response.body().string(),HttpStatus.OK);
		} catch(IOException e) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		
	}
	
}
