package com.infotel.eshop.service;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDate;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import com.infotel.eshop.config.TestConfig;
import com.infotel.eshop.model.Album;
import com.infotel.eshop.model.Book;
import com.infotel.eshop.model.Figure;
import com.infotel.eshop.model.Movie;
import com.infotel.eshop.model.Product;
import com.infotel.eshop.model.ProductFamily;
import com.infotel.eshop.model.ProductImage;
import com.infotel.eshop.model.ProductTag;

@RunWith(SpringRunner.class) @ContextConfiguration(classes=TestConfig.class)
public class SearchProductSpec extends AbstractTest {
	
	@Autowired
	private CatalogService service;
	
	@Test
	public void it_should_find_book_stupeur_et_tremblement() throws Exception {
		Product p = service.findProductById(1);
		
		assertThat(p)
			.isNotNull()
			.extracting(Product::getId, Product::getName, pr -> getHash(pr.getDescription()), Product::getPrice)
			.containsExactly(1, "Stupeur et tremblements", "PY/PLIjz8MrEdlhCAi5w8JwHgqQ=", 5.32);
		
		assertThat(p)
			.isInstanceOf(Book.class)
			.extracting("isbn", "releaseDate", "pages", "language")
			.containsExactly("978-2253150718", LocalDate.of(2001, 5, 30), 186, "fr" );
		
		assertThat(p.getFamily())
			.isNotNull()
			.extracting(ProductFamily::getId, ProductFamily::getName)
			.containsExactly(2, "Roman");
		
		assertThat(p.getImage())
			.isNotNull()
			.extracting(ProductImage::getId)
			.isEqualTo(1);
		
		Book book = (Book)p;
		assertThat(book.getAuthors())
				.isNotNull()
				.doesNotContainNull()
				.hasSize(1);
		
		Figure author = book.getAuthors().get(0);
		assertThat(author)
				.extracting(Figure::getId, Figure::getName)
				.containsExactly(1, "Amélie Nothomb");
		
		List<ProductTag> tags = p.getTags();
		assertThat(tags)
			.isNullOrEmpty();
	}
	
	@Test
	public void it_should_find_book_le_voyage_dhiver() throws Exception {
		Product p = service.findProductById(2);
		
		assertThat(p)
			.isNotNull()
			.extracting(Product::getId, Product::getName, pr -> getHash(pr.getDescription()), Product::getPrice)
			.containsExactly(2, "Le voyage d'hiver", "C1aAp9ejbXXerkOapp68LijQjGQ=", 4.85);
		
		assertThat(p)
			.isInstanceOf(Book.class)
			.extracting("isbn", "releaseDate", "language" ,"pages")
			.containsExactly("978-2253160151", LocalDate.of(2011, 05, 04), "fr", 128);
		
		assertThat(p.getFamily())
			.isNotNull()
			.extracting(ProductFamily::getId, ProductFamily::getName)
			.containsExactly(2, "Roman");

		assertThat(p.getImage())
			.isNotNull()
			.extracting(ProductImage::getId)
			.isEqualTo(2);
		
		Book book = (Book)p;
		assertThat(book.getAuthors())
				.isNotNull()
				.doesNotContainNull()
				.hasSize(1);
		
		Figure author = book.getAuthors().get(0);
		assertThat(author)
				.extracting(Figure::getId, Figure::getName)
				.containsExactly(1, "Amélie Nothomb");
		
		List<ProductTag> tags = p.getTags();
		assertThat(tags)
			.isNullOrEmpty();
	}
	
	@Test
	public void it_should_find_movie_intouchable() throws Exception {
		Product p = service.findProductById(28);
		
		assertThat(p)
			.isNotNull()
			.extracting(Product::getId, Product::getName, pr -> getHash(pr.getDescription()), Product::getPrice)
			.containsExactly(28, "Intouchables", "kOKLEN1ITQXZZpkGTq0tzsi9xEs=", 8.00);
		
		assertThat(p)
			.isInstanceOf(Movie.class)
			.extracting("releaseDate", "length")
			.containsExactly(LocalDate.of(2012, 03, 28), 107);

		assertThat(p.getFamily())
			.isNotNull()
			.extracting(ProductFamily::getId, ProductFamily::getName)
			.containsExactly(17, "Comédie");

		assertThat(p.getImage())
			.isNotNull()
			.extracting(ProductImage::getId)
			.isEqualTo(26);
		
		Movie movie = (Movie)p;
		List<Figure> actors = movie.getActors();
		
		assertThat(actors)
			.isNotNull()
			.doesNotContainNull()
			.hasSize(3);
		
		assertThat(actors.get(0))
			.extracting(Figure::getId, Figure::getName)
			.containsExactly(27, "François Cluzet");
		
		assertThat(actors.get(1))
			.extracting(Figure::getId, Figure::getName)
			.containsExactly(28, "Omar Sy");
		
		assertThat(actors.get(2))
			.extracting(Figure::getId, Figure::getName)
			.containsExactly(29, "Anne Le Ny");
		
		assertThat(movie.getDirector())
			.isNotNull()
			.extracting(Figure::getId, Figure::getName)
			.containsExactly(26,"Eric Toledano");	
		
		assertThat(movie.getLanguages())
			.isNotNull()
			.doesNotContainNull()
			.hasSize(1)
			.contains("fr");
		
		List<ProductTag> tags = p.getTags();
		assertThat(tags)
			.isEmpty();
		
	}
	
	@Test
	public void it_should_find_movie_la_grande_vadrouille() throws Exception {
		Product p = service.findProductById(30);
		
		assertThat(p)
		.isNotNull()
		.extracting(Product::getId, Product::getName, pr -> getHash(pr.getDescription()), Product::getPrice)
		.containsExactly(30, "La grande vadrouille", "xtHL87/xjQZurRHZEQhfvALz2Hs=", 9.00);
		
		assertThat(p)
		.isInstanceOf(Movie.class)
		.extracting("releaseDate", "length")
		.containsExactly(LocalDate.of(2003, 11, 03), 118);
		
		assertThat(p.getFamily())
		.isNotNull()
		.extracting(ProductFamily::getId, ProductFamily::getName)
		.containsExactly(17, "Comédie");
		
		assertThat(p.getImage())
		.isNotNull()
		.extracting(ProductImage::getId)
		.isEqualTo(28);
		
		Movie movie = (Movie)p;
		List<Figure> actors = movie.getActors();
		
		assertThat(actors)
			.isNotNull()
			.doesNotContainNull()
			.hasSize(2);
		
		assertThat(actors.get(0))
			.extracting(Figure::getId, Figure::getName)
			.containsExactly(33, "Bourvil");
		
		assertThat(actors.get(1))
			.extracting(Figure::getId, Figure::getName)
			.containsExactly(34, "Louis de Funès");
		
		Figure director = movie.getDirector();
		assertThat(director)
			.isNotNull()
			.extracting(Figure::getId, Figure::getName)
			.containsExactly(32,"Gérard Oury");
		
		assertThat(movie.getLanguages())
			.isNotNull()
			.doesNotContainNull()
			.hasSize(1)
			.contains("fr");
		
		List<ProductTag> tags = p.getTags();
		assertThat(tags)
			.isNullOrEmpty();
	}
	
	@Test
	public void it_should_find_album_tchaikovsky() throws Exception {
		Product p = service.findProductById(29);
		
		assertThat(p)
		.isNotNull()
		.extracting(Product::getId, Product::getName, pr -> getHash(pr.getDescription()), Product::getPrice)
		.containsExactly(29, "Tchaïkovsky : concerto pour piano n°1", "P37l9I6aHM2IXMn2pWxbtoy2cNI=", 6.00);
		
		assertThat(p)
		.isInstanceOf(Album.class)
		.extracting("releaseDate", "length")
		.containsExactly(LocalDate.of(1999, 10, 30), 115);
		
		assertThat(p.getFamily())
		.isNotNull()
		.extracting(ProductFamily::getId, ProductFamily::getName)
		.containsExactly(36, "Concerto");
			
		assertThat(p.getImage())
		.isNotNull()
		.extracting(ProductImage::getId)
		.isEqualTo(27);
		
		Album album = (Album)p;
		List<Figure> artists = album.getArtists();
		
		assertThat(artists)
			.isNotNull()
			.doesNotContainNull()
			.hasSize(2);
	
		assertThat(artists.get(0))
			.extracting(Figure::getId, Figure::getName)
			.containsExactly(30, "Martha Argerich");
	
		assertThat(artists.get(1))
			.extracting(Figure::getId, Figure::getName)
			.containsExactly(31, "Nicolas Economou");
		
		List<ProductTag> tags = p.getTags();
		assertThat(tags)
			.isNullOrEmpty();
	}
	
	@Test
	public void it_should_find_album__les_50_plus_belles_chansons_africaines() throws Exception {
		Product p = service.findProductById(63);
		
		assertThat(p)
			.isNotNull()
			.extracting(Product::getId, Product::getName, pr -> getHash(pr.getDescription()), Product::getPrice)
			.containsExactly(63, "Les 50 plus belles chansons africaines", "Nxf3AVJT6V05iilNKVv/G63Wexc=", 20.00);
		
		assertThat(p)
			.isInstanceOf(Album.class)
			.extracting("releaseDate", "length")
			.containsExactly(LocalDate.of(2012, 10, 22), 180);
		
		assertThat(p.getFamily())
			.isNotNull()
			.extracting(ProductFamily::getId, ProductFamily::getName)
			.containsExactly(43, "Afrique");
		
		assertThat(p.getImage())
			.isNotNull()
			.extracting(ProductImage::getId)
			.isEqualTo(61);
		
		Album album = (Album)p;
		List<Figure> artists = album.getArtists();
		
		assertThat(artists)
			.isNotNull()
			.doesNotContainNull()
			.hasSize(5);
	
		assertThat(artists.get(0))
			.extracting(Figure::getId, Figure::getName)
			.containsExactly(112, "Amadou & Mariam");
	
		assertThat(artists.get(1))
			.extracting(Figure::getId, Figure::getName)
			.containsExactly(113, "Bona Richard");
		
		assertThat(artists.get(2))
			.extracting(Figure::getId, Figure::getName)
			.containsExactly(114, "Bonga");
		
		assertThat(artists.get(3))
			.extracting(Figure::getId, Figure::getName)
			.containsExactly(115, "Cheb Bilal");
		
		assertThat(artists.get(4))
			.extracting(Figure::getId, Figure::getName)
			.containsExactly(116, "Dibango Manu");
		
		List<ProductTag> tags = p.getTags();
		assertThat(tags)
			.isEmpty();
		
	}
	
	@Test //TODO ajouter 
	public void it_should_find_tags_for_album_the_blues_brothers() throws Exception {
		Product p = service.findProductById(107);
		
		List<ProductTag> tags = p.getTags();
		assertThat(tags.get(0))
			.isNotNull()
			.extracting(ProductTag::getId, ProductTag::getName)
			.containsExactly(3, "Top ventes");
		
		assertThat(tags.get(1))
			.isNotNull()
			.extracting(ProductTag::getId, ProductTag::getName)
			.containsExactly(4, "Promo");
	}
	
	@Test
	public void it_should_find_barbecue() throws Exception {
		Product p = service.findProductById(1458);
		
		assertThat(p)
			.isNotNull()
			.extracting(Product::getId, Product::getName, pr -> getHash(pr.getDescription()), Product::getPrice)
			.containsExactly(1458, "Barbecue" ,"XEwjXoU45XI9KFbfh8G25ljVU4A=", 9.0);
		
		assertThat(p.getFamily())
			.isNotNull()
			.extracting(ProductFamily::getId, ProductFamily::getName)
			.containsExactly(1, "Littérature");
	}
	
	
	@Test
	public void it_should_return_null() throws Exception {
		Product p = service.findProductById(999999);
		
		assertThat(p).isNull();
	}
	
	@Test
	public void it_should_return_null_when_id_is_0() throws Exception {
		Product p = service.findProductById(0);
		
		assertThat(p).isNull();
	}
	
	@Test
	public void it_should_find_items_in_all_families() throws Exception {
		List<Product> products = (List<Product>) service.findProductByCriteria("tintin", -1, -1, false).get("products");
		
		assertThat(products)
			.isNotNull()
			.hasSize(8)
			.doesNotContainNull()
			.extracting(Product::getName)
			.allMatch(name -> name.toLowerCase().contains("tintin"));
		
		assertThat(products.get(0))
			.isNotNull()
			.extracting(Product::getId, Product::getName, Product:: getPrice)
			.containsExactly(114, "Les aventures de Tintin : Tintin au congo", 9.0);
		
		assertThat(products.get(0).getFamily())
			.isNotNull()
			.extracting(ProductFamily::getId, ProductFamily::getName)
			.containsExactly(7, "BD");
		
		assertThat(products.get(2))
			.isNotNull()
			.extracting(Product::getId, Product::getName, Product:: getPrice)
			.containsExactly(149, "Les aventures de Tintin : Les Cigares du pharaon", 10.95);
		
		assertThat(products.get(2).getFamily())
			.isNotNull()
			.extracting(ProductFamily::getId, ProductFamily::getName)
			.containsExactly(7, "BD");

		assertThat(products.get(7))
			.isNotNull()
			.extracting(Product::getId, Product::getName, Product:: getPrice)
			.containsExactly(1718, "Les trésors de Tintin : 22 fac-similés rares extraits des archives d'Hergé", 34.0);
		
		assertThat(products.get(7).getFamily())
			.isNotNull()
			.extracting(ProductFamily::getId, ProductFamily::getName)
			.containsExactly(1, "Littérature");
		
	}
	
	@Test
	public void it_should_find_items_in_one_family() throws Exception {
		List<Product> products = (List<Product>) service.findProductByCriteria("tintin", 7, -1, false).get("products");
		
		assertThat(products)
			.isNotNull()
			.hasSize(7)
			.extracting(Product::getName)
			.allMatch(name -> name.toLowerCase().contains("tintin"));
		
		assertThat(products.get(0))
			.isNotNull()
			.extracting(Product::getId, Product::getName, Product::getPrice)
			.containsExactly(114, "Les aventures de Tintin : Tintin au congo", 9.0 );
		
		assertThat(products.get(0).getFamily())
			.isNotNull()
			.extracting(ProductFamily::getId, ProductFamily::getName)
			.containsExactly(7, "BD");
		
		assertThat(products.get(2))
			.isNotNull()
			.extracting(Product::getId, Product::getName, Product::getPrice)
			.containsExactly(149, "Les aventures de Tintin : Les Cigares du pharaon", 10.95);
		
		assertThat(products.get(2).getFamily())
			.isNotNull()
			.extracting(ProductFamily::getId, ProductFamily::getName)
			.containsExactly(7, "BD");
		
		assertThat(products.get(6))
			.isNotNull()
			.extracting(Product::getId, Product::getName, Product::getPrice)
			.containsExactly(736, "Les Aventures de Tintin, tome 24 : Tintin et l'Alph-art", 10.0);
		
		assertThat(products.get(2).getFamily())
			.isNotNull()
			.extracting(ProductFamily::getId, ProductFamily::getName)
			.containsExactly(7, "BD");
	}
	
	@Test
	public void it_should_find_java_in_all_families() throws Exception {
		List<Product> products = (List<Product>) service.findProductByCriteria("java", -1, -1, false).get("products");
		
		assertThat(products)
			.isNotNull()
			.hasSize(10)
			.extracting(Product::getName)
			.allMatch(name -> name.toLowerCase().contains("java"));
		
		assertThat(products.get(0))
			.isNotNull()
			.extracting(Product::getId, Product::getName, Product::getPrice)
			.containsExactly(148, "Java pour les nuls", 21.0);

		assertThat(products.get(4))
			.isNotNull()
			.extracting(Product::getId, Product::getName, Product::getPrice)
			.containsExactly(526, "Programmer en Java : Couvre les nouveautés de Java 8, streams, expressions lambda", 36.0);
		
		assertThat(products.get(9))
			.isNotNull()
			.extracting(Product::getId, Product::getName, Product::getPrice)
			.containsExactly(582, "Java 8 - Les fondamentaux du langage Java (avec exercices et corrigés)", 29.0);
	}
	
	@Test
	public void it_should_find_java_in_one_family() throws Exception {
		List<Product> products = (List<Product>) service.findProductByCriteria("java", 1, -1, false).get("products");
		
		assertThat(products)
			.isNotNull()
			.hasSize(1)
			.extracting(Product::getName)
			.allMatch(name -> name.toLowerCase().contains("java"));
		
		assertThat(products.get(0))
			.isNotNull()
			.extracting(Product::getId, Product::getName, Product::getPrice)
			.containsExactly(1699, "Agile Java Development with Spring, Hibernate and Eclipse", 51.0);
	}
	
	@Test
	public void it_should_return_no_items() throws Exception {
		List<Product> products = (List<Product>) service.findProductByCriteria("xxxx", -1, -1, false).get("products");
		
		assertThat(products)
			.isNotNull()
			.isEmpty();
	}
	
	@Test
	public void it_should_return_max_10_items_when_keyword_is_null() throws Exception { // retourne tous les résultats : soit on renvoit rien soit les premiers resultats
		List<Product> products = (List<Product>) service.findProductByCriteria(null, -1, -1, false).get("products");
		
		assertThat(products)
			.isNotNull()
			.hasSize(10);
	}
	
	@Test
	public void it_should_return_no_items_when_keyword_is_blank() throws Exception { // retourne tous les résultats : soit on renvoit rien soit les premiers resultats
		List<Product> products = (List<Product>) service.findProductByCriteria("", -1, -1, false).get("products");
		
		assertThat(products)
			.isNotNull()
			.hasSize(10);
	}
	
	@Test
	public void it_should_return_no_items_when_keyword_null_when_one_family() throws Exception { // retourne tous les résultats : soit on renvoit rien soit les premiers resultats
		List<Product> products = (List<Product>) service.findProductByCriteria(null, 1, -1, false).get("products");
		
		assertThat(products)
		.isNotNull()
		.hasSize(10);
	}
	
	@Test
	public void it_should_return_empty_when_id_is_not_found() throws Exception { // retourne tous les résultats : soit on renvoit rien soit les premiers resultats
		List<Product> products = (List<Product>) service.findProductByCriteria("tintin", 99999, -1, false).get("products");
		
		assertThat(products)
			.isNotNull()
			.isEmpty();
	}
}
