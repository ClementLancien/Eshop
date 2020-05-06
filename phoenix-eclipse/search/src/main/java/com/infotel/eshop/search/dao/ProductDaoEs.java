package com.infotel.eshop.search.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.lucene.search.function.CombineFunction;
import org.elasticsearch.common.text.Text;
import org.elasticsearch.common.unit.Fuzziness;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.functionscore.ScoreFunctionBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightField;
import org.elasticsearch.search.suggest.Suggest;
import org.elasticsearch.search.suggest.SuggestBuilder;
import org.elasticsearch.search.suggest.SuggestBuilders;
import org.elasticsearch.search.suggest.SuggestionBuilder;
import org.elasticsearch.search.suggest.completion.CompletionSuggestion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;


import com.infotel.eshop.search.model.Album;
import com.infotel.eshop.search.model.Book;
import com.infotel.eshop.search.model.Movie;
import com.infotel.eshop.search.model.Product;
import com.infotel.eshop.search.model.ProductImage;
import com.infotel.eshop.search.model.SuggestItem;

@Repository 
public class ProductDaoEs implements ProductDao {

	private final static Logger log = LogManager.getLogger(ProductDaoEs.class);
	
	@Autowired
	private RestHighLevelClient client;
	
	@Override
	public Map<String, Object> findByCriteria(String keyword, int familyId, String tag, boolean randomize) throws Exception {
		Map<String, Object> resultat = new HashMap<>();
		List<Product> products = new ArrayList<>();
		
		QueryBuilder query = QueryBuilders.matchAllQuery();
		//BoolQueryBuilder query = QueryBuilders.m;
		List<QueryBuilder> filters = new ArrayList<QueryBuilder>();
		
		if (keyword != null && !keyword.isBlank()) {
			query = QueryBuilders.matchQuery("name", keyword).fuzziness(Fuzziness.ONE);
		}
		
		if (familyId != -1) {
			QueryBuilder familyFilter = QueryBuilders.termQuery("family.id", familyId);
			filters.add(familyFilter);
//			query = QueryBuilders.boolQuery()
//								 .must(query)
//								 .filter(familyFilter);
		}
		
		if (!tag.isEmpty()) {
			QueryBuilder tagsFilter = QueryBuilders.termQuery("tags.keyword", tag);
			filters.add(tagsFilter);
		}
		
		if (randomize) {
			query = QueryBuilders.functionScoreQuery(query, ScoreFunctionBuilders.randomFunction())
					.boostMode(CombineFunction.REPLACE);
		}
		

		BoolQueryBuilder queryBuilder = QueryBuilders.boolQuery().must(query);
		if (!filters.isEmpty()) {
			for(QueryBuilder filter : filters) {
				queryBuilder.should(filter);
			}
		}
		
		HighlightBuilder.Field hightlightField = new HighlightBuilder.Field("name");
		hightlightField.highlighterType("unified");
		
		
		HighlightBuilder highlighBuilder = new HighlightBuilder();
		highlighBuilder.field(hightlightField);
		highlighBuilder.preTags("<strong>");
		highlighBuilder.postTags("</strong>");
		
		SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();
		
		
		sourceBuilder.query(queryBuilder);

		String[] includes = {"name", "description", "type", "price", "imageId"};
		sourceBuilder.fetchSource(includes, null);
		sourceBuilder.highlighter(highlighBuilder);
		
		SearchRequest request = new SearchRequest("products");
		request.source(sourceBuilder);
		
		try {
			SearchResponse resp = client.search(request, RequestOptions.DEFAULT);
			resultat.put("sizeCollection", (int) resp.getHits().getTotalHits());
			for(SearchHit hit: resp.getHits().getHits()) {
				int id = Integer.parseInt(hit.getId());
				Map<String, Object> source = hit.getSourceAsMap();
				
				ProductImage image = null;
				if (source.get("imageId") != null) {
					int imageId = (int) source.get("imageId");
					image = new ProductImage();
					image.setId(imageId);
				}
				String type = (String) source.get("type");
				
				switch (type) {
				case "Book":
					Book book = new Book();
					book.setId(id);
					book.setName((String)source.get("name"));
					book.setImage(image);
					book.setPrice((double) source.get("price"));
					book.setDescription((String)source.get("description"));
					products.add(book);
					break;
					
				case "Movie":
					Movie movie = new Movie();
					movie.setId(id);
					movie.setName((String)source.get("name"));
					movie.setImage(image);
					movie.setPrice((double) source.get("price"));
					movie.setDescription((String)source.get("description"));
					products.add(movie);
					break;
					
				case "Album":
					Album album = new Album();
					album.setId(id);
					album.setName((String)source.get("name"));
					album.setImage(image);
					album.setPrice((double) source.get("price"));
					album.setDescription((String)source.get("description"));
					products.add(album);
					break;
					
				}
				
				Map<String, HighlightField> highlightFields = hit.getHighlightFields();
				if (highlightFields != null) {
					HighlightField field = highlightFields.get("name");
					if (field != null) {
						Text[] fragments = field.fragments();
						String name = fragments[0].toString();
						Product p = products.get(products.size() - 1);
						p.setName(name);
					}
				}
			resultat.put("products", products);
			}
		} catch (Exception e) {
			log.error("Echec lecture des produits", e);
		}
		
		return resultat;
	}

	@Override
	public List<Product> findByTag(int tagId) throws Exception {
		return null;
	}

	@Override
	public Product findById(int id) throws Exception {
		return null;
	}
	
	@Override
	public List<SuggestItem> findSuggest(String keyword, int familyId) {
		List<SuggestItem> suggests = new ArrayList<>();
		
		try {
			SuggestionBuilder<?> suggestionBuilder = 
					SuggestBuilders.completionSuggestion("suggest")
									.text(keyword);
			
			SuggestBuilder suggestBuilder = new SuggestBuilder();
			suggestBuilder.addSuggestion("name_suggest", suggestionBuilder); // name_suggest local
			
			
			SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();
			sourceBuilder.suggest(suggestBuilder);
			sourceBuilder.fetchSource(false); // ne pas recuperer le document source json
			
			SearchRequest request = new SearchRequest("products");
			request.source(sourceBuilder);
			
			SearchResponse response = client.search(request, RequestOptions.DEFAULT);
			Suggest suggest = response.getSuggest();
			
			CompletionSuggestion completionSuggestion = suggest.getSuggestion("name_suggest");
			for(CompletionSuggestion.Entry entry : completionSuggestion.getEntries()) {
				
				for(CompletionSuggestion.Entry.Option option : entry.getOptions()) {
					String suggestText = option.getText().toString();					
					
					SuggestItem sugg = new SuggestItem();
					sugg.setId(Integer.parseInt(option.getHit().getId()));
					sugg.setName(suggestText);
					sugg.setType("");
					
					suggests.add(sugg);
				}
			}
			
		} catch (Exception e) {
			log.error("Echec lecture des suggestions", e);
		}
		
		return suggests;
	}

}
