package com.infotel.eshop.service;

import java.io.File;
import java.net.MalformedURLException;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Base64;

import org.dbunit.DatabaseUnitException;
import org.dbunit.database.DatabaseConnection;
import org.dbunit.database.IDatabaseConnection;
import org.dbunit.dataset.DataSetException;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.ITable;
import org.dbunit.dataset.ReplacementDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;
import org.dbunit.operation.DatabaseOperation;

public class AbstractTest {

	protected void cleanInsert()
			throws MalformedURLException, DataSetException, Exception, DatabaseUnitException, SQLException {
		IDataSet dataset = loadDataSet("src/test/resources/datasets/clean-insert.xml");
		IDatabaseConnection cn = getConnection();
		DatabaseOperation.CLEAN_INSERT.execute(cn, dataset);
		cn.close();
	}

	protected ITable loadActualTable(String tableName) throws Exception {
		IDatabaseConnection cn = getConnection();
		ITable table = cn.createDataSet().getTable(tableName);
		cn.close();
		return table;
	}
	
	protected IDataSet loadDataSet(String filename) throws MalformedURLException, DataSetException {
		FlatXmlDataSetBuilder builder = new FlatXmlDataSetBuilder(); // parse doc xml et le transforme en Idatasaset
		IDataSet dataset = builder.build(new File(filename));
		return dataset;
	}
	
	protected ReplacementDataSet getReplacement(IDataSet dataset) {
		ReplacementDataSet replacement = new ReplacementDataSet(dataset);
		replacement.addReplacementObject("[null]", null);
		replacement.addReplacementObject("[now]", Timestamp.valueOf(LocalDateTime.now().withSecond(0).withNano(0)));
		return replacement;
	}

	private IDatabaseConnection getConnection() throws Exception {
		Class.forName("com.mysql.jdbc.Driver");
		String url = "jdbc:mysql://localhost:3306/eshop_test?useSSL=false";
		Connection cn = DriverManager.getConnection(url, "scott", "tiger");
		return new DatabaseConnection(cn);
	}

	protected String getHash(String str) { // methode utilitaire https://www.baeldung.com/sha-256-hashing-java
		try {
			MessageDigest digest = MessageDigest.getInstance("SHA-1");
			byte[] encodedhash = digest.digest(str.getBytes(StandardCharsets.UTF_8));
			return Base64.getEncoder().encodeToString(encodedhash);
		} catch (NoSuchAlgorithmException e) {
			return null;
		}
	}

	protected String getHash(byte[] bytes) { // methode utilitaire https://www.baeldung.com/sha-256-hashing-java
		try {
			MessageDigest digest = MessageDigest.getInstance("SHA-1");
			byte[] encodedhash = digest.digest(bytes);
			return Base64.getEncoder().encodeToString(encodedhash);
		} catch (NoSuchAlgorithmException e) {
			return null;
		}
	}
	
	protected String transformDateToString(LocalDate date) {
		DateTimeFormatter dateFmt = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		return date.format(dateFmt);
	}

}