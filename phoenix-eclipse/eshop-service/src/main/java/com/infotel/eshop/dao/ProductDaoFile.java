package com.infotel.eshop.dao;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.StringTokenizer;

import com.infotel.eshop.exception.EShopException;
import com.infotel.eshop.model.Product;

public class ProductDaoFile implements ProductDao {

	private List<Product> productTable = new ArrayList<>();
	
	public ProductDaoFile() {
		super();
		loadProducts();
	}

	private void loadProducts() {
		
		try (
				InputStream is = new FileInputStream("products.txt");
				Scanner scanner = new Scanner(is);
			) {
			while (scanner.hasNextLine() ) {
				String line = scanner.nextLine();
				StringTokenizer tokenizer = new StringTokenizer(line, ";");
				int id = Integer.parseInt(tokenizer.nextToken());
				String name = tokenizer.nextToken();
				double price = Double.parseDouble(tokenizer.nextToken());
				//LocalDate date = LocalDate.parse(tokenizer.nextToken());
				
				Product product = new Product();
				product.setId(id);
				product.setName(name);
				product.setPrice(price);
				//product.setReleaseDate(date);
				
				productTable.add(product);
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		} 
	}
	
	@Override
	public Map<String, Object> findByCriteria(String keyword, int familyId, int tagId, boolean randomize) {
	//public List<Product> findByCriteria(String keyword, int familyId, int tagId, boolean randomize) {
		List<Product> productArray = new ArrayList<>();
		for (Product product : productTable) {
			if(product.getName().toLowerCase().contains(keyword.toLowerCase())) {
				productArray.add(product);
			}
		}
		return null;
		//return productArray;
		
//		List<Product> productArray = new ArrayList<>();
//		Charset cs = Charset.forName("UTF-8");
//		try (
//				InputStream is = new FileInputStream("products.txt");
//				Scanner scanner = new Scanner(is, cs);
//			)
//		{
//			
//			while(scanner.hasNextLine()) {
//				String line = scanner.nextLine();
////				String token = tokenizer.nextToken();
//				StringTokenizer tokenizer = new StringTokenizer(line, ";");
//				int id = Integer.parseInt(tokenizer.nextToken());
//				String name = tokenizer.nextToken();
//				double price = Double.parseDouble(tokenizer.nextToken());
//				
//				if (name.contains(keyword)) {
//					Product product = new Product();
//					product.setId(id);
//					product.setName(name);
//					product.setPrice(price);
//					productArray.add(product);
//				}
//			}
//			
//		} catch (IOException ioe) {
//			ioe.printStackTrace();
//		}
//		return productArray;
	}

	@Override
	public Product findById(int id) {
		for (Product product : productTable) {
			if (product.getId() == id) {
				return product;
			}
		}
		return null;
	}

	@Override
	public List<Product> findByTag(int id) throws EShopException {
		// TODO Auto-generated method stub
		return null;
	}

		
//		Product product = new Product();
//		Charset cs = Charset.forName("UTF-8");
//		try (
//				InputStream is = new FileInputStream("products.txt");
//				Scanner scanner = new Scanner(is, cs);
//			)
//		{
//			while(scanner.hasNextLine()) {
//				String line = scanner.nextLine();
//				StringTokenizer tokenizer = new StringTokenizer(line, ";");
//				int productId = Integer.parseInt(tokenizer.nextToken());
//				String name = tokenizer.nextToken();
//				double price = Double.parseDouble(tokenizer.nextToken());
//				
//				if (productId == id) {
//					product.setId(id);
//					product.setName(name);
//					product.setPrice(price);
//				}
//			}
//			
//		} catch (IOException ioe) {
//			ioe.printStackTrace();
//		}
//		return product;
//	}

}
