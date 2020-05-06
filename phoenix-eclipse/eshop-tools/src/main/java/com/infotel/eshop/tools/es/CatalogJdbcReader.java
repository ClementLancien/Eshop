package com.infotel.eshop.tools.es;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.infotel.eshop.tools.es.model.Figure;
import com.infotel.eshop.tools.es.model.Product;
import com.infotel.eshop.tools.es.model.ProductFamily;
import com.infotel.eshop.tools.es.model.ProductImage;

public class CatalogJdbcReader implements AutoCloseable {
	
	public CatalogJdbcReader() throws ClassNotFoundException, SQLException {
		openConnection();
	}

	private Connection connection;
	
	private List<ProductFamily> families;
	
	public List<Product> findProducts() throws Exception {
		List<Product> products = new ArrayList<>();
		
		Statement st = null;
		ResultSet rs = null;
		try {
			st = connection.createStatement();
			rs = st.executeQuery("select * from product");
			while (rs.next()) {
				Product p = new Product();
				p.setType("Product");
				p.setId(rs.getInt("id"));
				p.setName(rs.getString("name"));
				p.setDescription(rs.getString("description"));
				p.setPrice(rs.getDouble("price"));
				p.setAvailable(rs.getBoolean("available"));
				
				int familyId = rs.getInt("family_id");
				p.setFamily(findFamily(familyId).clone(false));
				p.setImageId(rs.getInt("image_id"));
				if (rs.wasNull()) {
					p.setImageId(-1);
				}
				//p.setImage(image);
				
				products.add(p);
				
				// derived objects
				populateBook(p);
				populateMovie(p);
				populateAlbum(p);
				populateTags(p);
			}
		} finally {
			close(rs, st);
		}
		
		return products;
	}
	
	private void populateBook(Product p) throws Exception {
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			String sql = "select * from book where id = ?";
			st = connection.prepareStatement(sql);
			st.setInt(1, p.getId());
			rs = st.executeQuery();
			if (rs.next()) {
				p.setType("Book");
				p.setReleaseDate(rs.getDate("release_date"));
				p.setIsbn(rs.getString("isbn"));
				p.setLanguage(rs.getString("language"));
				p.setPages(rs.getInt("pages"));
				
				populateAuthors(p);
			}
		} finally {
			close(rs, st);
		}
	}
	
	private void populateMovie(Product p) throws Exception {
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			String sql = "select * from movie where id = ?";
			st = connection.prepareStatement(sql);
			st.setInt(1, p.getId());
			rs = st.executeQuery();
			if (rs.next()) {
				p.setType("Movie");
				p.setReleaseDate(rs.getDate("release_date"));
				p.setLength(rs.getInt("length"));
			
				populateMovieLanguages(p);
				populateActors(p);
				populateDirector(p);
			}
		} finally {
			close(rs, st);
		}
	}

	private void populateAlbum(Product p) throws Exception {
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			String sql = "select * from album where id = ?";
			st = connection.prepareStatement(sql);
			st.setInt(1, p.getId());
			rs = st.executeQuery();
			if (rs.next()) {
				p.setType("Album");
				p.setReleaseDate(rs.getDate("release_date"));
				p.setLength(rs.getInt("length"));
				
				populateArtists(p);
			}
		} finally {
			close(rs, st);
		}
	}
	
	private void populateTags(Product p) throws Exception {
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			String sql = "select t.name from product_product_tag ppt, product_tag t "
					+ "where ppt.product_id = ? and ppt.tag_id=t.id";
			st = connection.prepareStatement(sql);
			st.setInt(1, p.getId());
			rs = st.executeQuery();
			while (rs.next()) {
				p.addTag(rs.getString(1));
			}
		} finally {
			close(rs, st);
		}		
	}
	
	private void populateMovieLanguages(Product p) throws Exception {
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			String sql = "select language from movie_language "
					+ "where movie_id = ?";
			st = connection.prepareStatement(sql);
			st.setInt(1, p.getId());
			rs = st.executeQuery();
			while (rs.next()) {
				p.addMovieLanguage(rs.getString(1));
			}
		} finally {
			close(rs, st);
		}		
	}
	
	private void populateAuthors(Product p) throws Exception {
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			String sql = "select * from book_author ba, figure f "
					+ "where ba.book_id = ? and ba.author_id = f.id";
			st = connection.prepareStatement(sql);
			st.setInt(1, p.getId());
			rs = st.executeQuery();
			while (rs.next()) {
				Figure a = new Figure();
				a.setId(rs.getInt("id"));
				a.setName(rs.getString("name"));
				p.addAuthor(a);
			}
		} finally {
			close(rs, st);
		}		
	}
	
	private void populateActors(Product p) throws Exception {
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			String sql = "select * from movie_actor ma, figure f "
					+ "where ma.movie_id = ? and ma.actor_id = f.id";
			st = connection.prepareStatement(sql);
			st.setInt(1, p.getId());
			rs = st.executeQuery();
			while (rs.next()) {
				Figure a = new Figure();
				a.setId(rs.getInt("id"));
				a.setName(rs.getString("name"));
				p.addActor(a);
			}
		} finally {
			close(rs, st);
		}		
	}

	private void populateDirector(Product p) throws Exception {
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			String sql = "select * from movie_director md, figure f "
					+ "where md.movie_id = ? and md.director_id = f.id";
			st = connection.prepareStatement(sql);
			st.setInt(1, p.getId());
			rs = st.executeQuery();
			if (rs.next()) {
				Figure d = new Figure();
				d.setId(rs.getInt("id"));
				d.setName(rs.getString("name"));
				p.setDirector(d);;
			}
		} finally {
			close(rs, st);
		}		
	}

	private void populateArtists(Product p) throws Exception {
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			String sql = "select * from album_artist aa, figure f "
					+ "where aa.album_id = ? and aa.artist_id = f.id";
			st = connection.prepareStatement(sql);
			st.setInt(1, p.getId());
			rs = st.executeQuery();
			while (rs.next()) {
				Figure a = new Figure();
				a.setId(rs.getInt("id"));
				a.setName(rs.getString("name"));
				p.addArtist(a);
			}
		} finally {
			close(rs, st);
		}		
	}

	private ProductFamily findFamily(int id) throws Exception {
		List<ProductFamily> families = findFamilies();
		for (ProductFamily f : families) {
			if (f.getId() == id) return f;
		}
		return null;
	}

	public List<ProductFamily> findFamilies() throws Exception {
		if (families != null) return families;
		
		families = new ArrayList<>();
		Map<Integer, List<ProductFamily>> mapFamilyIdSubFamilies = new HashMap<>();
		
		Statement st = null;
		ResultSet rs = null;
		try {
			st = connection.createStatement();
			rs = st.executeQuery("select * from product_family");
			while (rs.next()) {
				ProductFamily f = new ProductFamily();
				f.setId(rs.getInt("id"));
				f.setName(rs.getString("name"));
				int parentId = rs.getInt("parent_id");
				if (!rs.wasNull()) {
					if (mapFamilyIdSubFamilies.get(parentId) == null) {
						mapFamilyIdSubFamilies.put(parentId, new ArrayList<ProductFamily>());
					}
					mapFamilyIdSubFamilies.get(parentId).add(f);
				}
				
				families.add(f);
				
				// set sub families
				Set<Map.Entry<Integer, List<ProductFamily>>> entries 
							= mapFamilyIdSubFamilies.entrySet();
				for (Map.Entry<Integer, List<ProductFamily>> entry : entries) {
					ProductFamily parent = findFamily(entry.getKey());
					parent.setSubFamilies(entry.getValue());
				}
			}
		} finally {
			close(rs, st);
		}
		
		return families;
	}

	public List<ProductImage> findImages() throws Exception {
		List<ProductImage> images = new ArrayList<>();
		
		Statement st = null;
		ResultSet rs = null;
		try {
			st = connection.createStatement();
			rs = st.executeQuery("select * from product_image");
			while (rs.next()) {
				ProductImage img = new ProductImage();
				img.setId(rs.getInt("id"));
				Blob blob = rs.getBlob("content");
				byte[] buffer = new byte[1024];
		      	int length = 0;
		      	InputStream is = blob.getBinaryStream();
		      	ByteArrayOutputStream baos = new ByteArrayOutputStream();
		      	while((length = is.read(buffer)) != -1) {
		      	    baos.write(buffer, 0, length);
		      	}
				img.setContent(baos.toByteArray());
				images.add(img);				
			}
		} finally {
			close(rs, st);
		}
		
		return images;
	}

	private void close(Connection cn) {
		try {
			if (cn != null) try {cn.close();} catch (Exception e){}
		} catch (Exception e) {}
	}

	private void close(ResultSet rs, Statement st) {
		try {
			if (rs != null) try {rs.close();} catch (Exception e){}
		} catch (Exception e) {}
		try {
			if (st != null) try {st.close();} catch (Exception e){}
		} catch (Exception e) {}
	}
	
	private void openConnection() throws SQLException, ClassNotFoundException {
        String driver = "com.mysql.jdbc.Driver";
        String url = "jdbc:mysql://localhost:3306/eshop?useSSL=false";
        String username = "scott";
        String password = "tiger";
		
        Class.forName(driver);
        
		connection = 
				DriverManager.getConnection(url, username, password);
	}

	@Override
	public void close() throws Exception {
		close(connection);
	}

}
