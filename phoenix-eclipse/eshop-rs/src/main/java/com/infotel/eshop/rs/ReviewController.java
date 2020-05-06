package com.infotel.eshop.rs;

import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.infotel.eshop.dto.ReviewDto;

import okhttp3.HttpUrl;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

@RestController @RequestMapping("/reviews")
public class ReviewController {

	private final static Logger log = LogManager.getLogger(ReviewController.class);
	
	@Autowired
	private OkHttpClient client;
	
	@GetMapping("/{productId}")
	public ResponseEntity<String> findReviews(@PathVariable int productId) throws Exception {
		HttpUrl.Builder urlBuilder = HttpUrl.parse("http://localhost:8081" + "/reviews").newBuilder();
	    urlBuilder.addPathSegment(Integer.toString(productId));
	    
	    String url = urlBuilder.build().toString();
	    
	    if (log.isDebugEnabled()) {
	    	log.debug("url : " + url);
	    }
	    
		Request request = new Request.Builder()
                					 .url(url)
                					 .build();
		
		Response response = client.newCall(request).execute();
		if (log.isDebugEnabled()) {
		    	log.debug("url : " + url);
		    	log.debug("response : " + response.body().contentType());
		    	log.debug("response : " + response.body().toString());
		  }
		
		return new ResponseEntity<String>(response.body().string(), HttpStatus.OK);
	}
	
	@PutMapping
	public ResponseEntity<Void> createOrUpdate(@RequestBody ReviewDto dto) {
		HttpUrl.Builder urlBuilder = HttpUrl.parse("http://localhost:8081" + "/reviews").newBuilder();	    
	    String url = urlBuilder.build().toString();
	    
	    ObjectMapper mapper = new ObjectMapper();
	    
	    try {
			okhttp3.RequestBody body = okhttp3.RequestBody.create(mapper.writeValueAsString(dto), MediaType.get("application/json"));
			Request request = new Request.Builder()
			        					 .url(url)
			        					 .put(body)
			        					 .build();
			
			client.newCall(request).execute(); //Response response = 
			
			return new ResponseEntity<>(HttpStatus.OK);
		} catch (IOException e) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		
		//return response.body().string();
	}
}
