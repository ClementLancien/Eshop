package com.infotel.eshop.tools.images;

import java.io.InputStream;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class ProductImageProcessor {
	
	private Connection connection;
	private ImageResizer imageResizer;
	
	private int width, height;
	
	public void process(int width, int height) throws Exception {
		System.out.println("Début traitement");
		
		try {
			init(width, height);
			addContentSmall();
			loadProducts();
			alterContentSmallNotNull();
		} finally {
			close();
		}

		System.out.println("Fin traitement");
	}
	
	private void addContentSmall() throws Exception {
		String sql = "alter table product_image add content_small mediumblob";
		try (PreparedStatement ps = connection.prepareStatement(sql)) {
			ps.executeUpdate();
		}
	}

	private void alterContentSmallNotNull() throws Exception {
		String sql = "alter table product_image modify content_small mediumblob not null";
		try (PreparedStatement ps = connection.prepareStatement(sql)) {
			ps.executeUpdate();
		}
	}
	
	private void loadProducts() throws Exception {
		try (Statement st = connection.createStatement();
			ResultSet rs = st.executeQuery("select id, content from product_image");) {
			while (rs.next()) {
				int id = rs.getInt(1);
				Blob blob = rs.getBlob(2);
				InputStream imageStream = imageResizer.resize(blob.getBinaryStream(), width, height);
				
				updateProduct(id, imageStream);
			}
		}
	}
	
	private void init(int width, int height) throws Exception {
		this.width = width;
		this.height = height;
		imageResizer = new ImageResizer();
		
		Class.forName("com.mysql.jdbc.Driver");
		String url = "jdbc:mysql://localhost:3306/eshop_media";
		connection = DriverManager.getConnection(url, "scott", "tiger");
		connection.setAutoCommit(true);
	}
	
	private void updateProduct(int id, InputStream imageStream) throws Exception {
		
		String sql = "update product_image set content_small = ? where id = ?";
		
		try (PreparedStatement ps = connection.prepareStatement(sql);) {		
			ps.setBlob(1, imageStream);
			ps.setInt(2, id);
			ps.executeUpdate();
		}
	}
	
	private void close() {
		if (connection != null) {
			try {
				connection.close();
			} catch (Exception e) {}
		}
	}
}
