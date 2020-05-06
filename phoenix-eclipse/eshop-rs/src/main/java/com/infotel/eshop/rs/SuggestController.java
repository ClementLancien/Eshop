package com.infotel.eshop.rs;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.infotel.eshop.dto.SuggestDto;
import com.infotel.eshop.exception.EShopException;
import com.infotel.eshop.mapper.SuggestMapper;
import com.infotel.eshop.model.SuggestItem;
import com.infotel.eshop.service.CatalogService;

import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

@RestController @RequestMapping("/suggest")
public class SuggestController {
/****************************************************************OLD*********************************************/
//	@Autowired
//	private CatalogService service;
//	
//	@GetMapping
//	public List<SuggestDto> getSuggest(@RequestParam("q") String keyword, @RequestParam("id") int familyId) throws EShopException { // "q" pour query
//		System.err.println("familyId : " + familyId);
//		//List<Product> products = (List<Product>) service.findProductByCriteria(keyword, familyId, -1, false).get("products");
//		List<SuggestItem> products = service.findSuggest(keyword, familyId);
//		return products.stream()
//					   .map(SuggestMapper::suggestItemToSuggestDto)
//					   .collect(Collectors.toList());
//	}
	
	
	//http://localhost:8082/suggest/?q=tintin&id=7
/**********************************************************MS*******************************************************/
	
private final static Logger log = LogManager.getLogger(SuggestController.class);
	
	@Autowired
	private OkHttpClient client;
	
	@GetMapping
	public ResponseEntity<SuggestDto> getSuggest(@RequestParam("q") String keyword, @RequestParam("id") int familyId) throws EShopException {
		HttpUrl.Builder urlBuilder = HttpUrl.parse("http://localhost:8082" + "/suggest").newBuilder();
	    urlBuilder.addPathSegment(keyword);
	    urlBuilder.addPathSegment(Integer.toString(familyId));
	    
	    String url = urlBuilder.build().toString();
	    
	    if (log.isDebugEnabled()) {
	    	log.debug("url : " + url);
	    }
	    
		Request request = new Request.Builder()
                					 .url(url)
                					 .build();
		
		Response response;
		try {
			response = client.newCall(request).execute();
			if (log.isDebugEnabled()) {
				log.debug("url : " + url);
				log.debug("response : " + response.body().contentType());
				log.debug("response : " + response.body().toString());
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return new ResponseEntity<SuggestDto>(HttpStatus.OK);
	}
}
