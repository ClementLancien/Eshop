package com.infotel.eshop.tools.es;

import static org.elasticsearch.common.xcontent.XContentFactory.jsonBuilder;

import java.io.IOException;
import java.util.List;

import org.apache.http.HttpHost;
import org.elasticsearch.action.admin.indices.delete.DeleteIndexRequest;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.support.master.AcknowledgedResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.indices.CreateIndexRequest;
import org.elasticsearch.client.indices.CreateIndexResponse;
import org.elasticsearch.client.indices.PutMappingRequest;
import org.elasticsearch.common.Strings;
import org.elasticsearch.common.xcontent.XContentBuilder;
import org.elasticsearch.common.xcontent.XContentType;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.infotel.eshop.tools.es.model.Product;
import com.infotel.eshop.tools.es.model.ProductFamily;
import com.infotel.eshop.tools.es.model.ProductImage;

public class CatalogIndexWriter implements AutoCloseable {
	
	private ObjectMapper mapper;	
	private RestHighLevelClient client;

	public CatalogIndexWriter() {
		mapper = new ObjectMapper();
		mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);

		try {
			client = new RestHighLevelClient(
						RestClient.builder(
							new HttpHost("localhost", 9200, "http"),
							new HttpHost("localhost", 9201, "http")
						));			
			
			deleteIndex("products");
			createIndex("products");
			deleteIndex("families");
			createIndex("families");
			deleteIndex("images");
			createIndex("images");
		} catch (Exception e) {
			e.printStackTrace();
		}		
	}
	
	private void deleteIndex(String index) throws IOException {
		try {
			DeleteIndexRequest request = new DeleteIndexRequest(index);
			AcknowledgedResponse response;
			response = client.indices().delete(request, RequestOptions.DEFAULT);
			 
			System.out.println("Suppression index => " + response.isAcknowledged());
		} catch (Exception e) {}
	}

	private void createIndex(String index) throws IOException {
		CreateIndexRequest request = new CreateIndexRequest(index);
		CreateIndexResponse response = client.indices().create(request, RequestOptions.DEFAULT);
		
		System.out.println("Création index " + index + " => " + response.isAcknowledged());
		
		createMapping(index);
	}
	
	private void createMapping(String index) throws IOException {
		String mapping = null;
		switch (index) {
		case "products":
			mapping = Strings.toString(createProductMapping());
			break;

		case "families":
			mapping = Strings.toString(createFamilyMapping());
			break;
		}
		
		PutMappingRequest request = new PutMappingRequest(index);
		request.source(mapping, XContentType.JSON);
		AcknowledgedResponse response = client.indices().putMapping(request, RequestOptions.DEFAULT);
		
		System.out.println("Création mapping " + index + "=> " + response.isAcknowledged());
	}
			
	private XContentBuilder createProductMapping() throws IOException{
		XContentBuilder mapping = jsonBuilder()
                .startObject()
                      .startObject("properties")
                           .startObject("name")
                               .field("type", "text")
                               .field("analyzer", "french")
                               //.startObject("fields")
                               	//	.startObject("keyword") // le type keyword permet de faire du tri
                               		//	.field("type", "keyword")
                               		//.endObject()
                               //.endObject()
                           .endObject()
                           .startObject("description")
                           	   .field("type", "text")
                               .field("analyzer", "french")
                           .endObject()
                           .startObject("suggest")
                              .field("type","completion")
                              .field("analyzer", "french")
                           .endObject()
                      .endObject()
                   .endObject();
				
		return mapping;
	}

	private XContentBuilder createFamilyMapping() throws IOException{
		XContentBuilder mapping = jsonBuilder()
				.startObject()
					.startObject("properties")
						.startObject("name")
							.field("type", "text")
							.field("analyzer", "french")
						.endObject()
						.startObject("family_suggest")
							.field("type","completion")
							.field("analyzer", "french")
						.endObject()
					.endObject()
				.endObject();
		
		return mapping;
	}
	
	public void indexProducts(List<Product> products) throws Exception {
		System.out.println("\r\nProduits indexés :");
		for (Product p : products) {
			String json = mapper.writeValueAsString(p);
			//System.out.println(json);

			IndexRequest request = 
					new IndexRequest("products").id(Integer.toString(p.getId())); // pour conserver les id
			request.source(json, XContentType.JSON);
			
			IndexResponse response = client.index(request, RequestOptions.DEFAULT);
			
			System.out.println("\t" + p.getName() + " : version = " + response.getVersion());
		}
	}

	public void indexFamilies(List<ProductFamily> families) throws Exception {
		System.out.println("\r\nFamilles indexées :");
		for (ProductFamily f : families) {
			String json = mapper.writeValueAsString(f);

			IndexRequest request = 
					new IndexRequest("families").id(Integer.toString(f.getId()));
			request.source(json, XContentType.JSON);
			
			IndexResponse response = client.index(request, RequestOptions.DEFAULT);

			
			System.out.println("\t" + f.getName() + " : version = " + response.getVersion());
		}
	}
	
	public void indexImages(List<ProductImage> images) throws Exception {
		System.out.println("\r\nImages indexées :");
		for (ProductImage img : images) {
			String json = mapper.writeValueAsString(img);

			IndexRequest request = 
					new IndexRequest("images").id(Integer.toString(img.getId()));
			request.source(json, XContentType.JSON);
			
			IndexResponse response = client.index(request, RequestOptions.DEFAULT);
			
			System.out.println("\t" + img.getId() + " : version = " + response.getVersion());
		}
	}

	@Override
	public void close() throws Exception {
		client.close();
	}

}
