package com.infotel.eshop.service;

import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

import java.time.LocalDate;

import org.dbunit.Assertion;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.ITable;
import org.dbunit.dataset.filter.DefaultColumnFilter;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import com.infotel.eshop.config.TestConfig;
import com.infotel.eshop.exception.RegisterException;
import com.infotel.eshop.model.Address;
import com.infotel.eshop.model.Customer;
import com.infotel.eshop.model.CustomerDetail;

@RunWith(SpringRunner.class) @ContextConfiguration(classes=TestConfig.class)
public class RegisterCustomerSpec extends AbstractTest {
	
	@Autowired
	private UserService service;
	
	@Before
	public void init() throws Exception {
		cleanInsert();
	}

	@Test
	public void it_should_register_kobe_when_address_and_details_are_null() throws Exception {
		Customer cust =  new Customer();
		
		cust.setUsername("kobe@free.fr");
		cust.setPassword("basket");
		cust.setFirstName("Bryant");
		cust.setLastName("Kobe");
		cust.setAddress(null);
		cust.setDetail(null);
		
		service.registerCustomer(cust);
		
		// Assert
		ITable actualTable = loadActualTable("user_data");
		IDataSet expectedDataSet = getReplacement(loadDataSet("src/test/resources/datasets/register_customer/register-kobe.xml"));
		ITable expectedTable = expectedDataSet.getTable("user_data");
		
		actualTable = DefaultColumnFilter.includedColumnsTable(actualTable,
				expectedTable.getTableMetaData().getColumns());
		
		Assertion.assertEquals(expectedTable, actualTable);
		
		actualTable = loadActualTable("customer_detail");
		expectedDataSet = getReplacement(loadDataSet("src/test/resources/datasets/register_customer/register-kobe.xml"));
		expectedTable = expectedDataSet.getTable("customer_detail");
		
		actualTable = DefaultColumnFilter.includedColumnsTable(actualTable,
				expectedTable.getTableMetaData().getColumns()); // ne prend pas en compte la columne country
		
		Assertion.assertEquals(expectedTable, actualTable);
		
	}
	
	@Test 
	public void it_should_failed_when_user_exists() throws Exception {
		// Arrange
		Customer cust =  new Customer();
		
		cust.setUsername("roger@gmail.com");
		cust.setPassword("secret");
		cust.setFirstName("Roger");
		cust.setLastName("Rabbit");
		
		assertThatExceptionOfType(RegisterException.class)
			.isThrownBy(() -> 	service.registerCustomer(cust))
			.withNoCause();
	}
	
	@Test
	public void it_should_register_tony() throws Exception {
		// Arrange
		Customer cust =  new Customer();
		Address address = new Address();
		CustomerDetail detail = new CustomerDetail();
		
		cust.setUsername("tony.parker@free.fr");
		cust.setPassword("basket");
		cust.setFirstName("Tony");
		cust.setLastName("Parker");
		
		address.setStreet("18 rue des Roches");
		address.setPostcode("75019");
		address.setCity("Paris");
		address.setCountry("France");
		
		cust.setAddress(address);
		
		detail.setBirthDate(LocalDate.parse("1982-03-17"));
		detail.setPhone("0775757575");
		detail.setEmail("tony.parker@free.fr");
		detail.setCustomer(cust);
		cust.setDetail(detail);
		
		// Act
		service.registerCustomer(cust);
		
		// Assert
		ITable actualTable = loadActualTable("user_data");
		IDataSet expectedDataSet = getReplacement(loadDataSet("src/test/resources/datasets/register_customer/register-tony.xml"));
		ITable expectedTable = expectedDataSet.getTable("user_data");
		
		actualTable = DefaultColumnFilter.includedColumnsTable(actualTable,
				expectedTable.getTableMetaData().getColumns()); // ne prend pas en compte la columne country
		
		Assertion.assertEquals(expectedTable, actualTable);
		
		actualTable = loadActualTable("customer_detail");
		expectedDataSet = getReplacement(loadDataSet("src/test/resources/datasets/register_customer/register-tony.xml"));
		expectedTable = expectedDataSet.getTable("customer_detail");
		
		actualTable = DefaultColumnFilter.includedColumnsTable(actualTable,
				expectedTable.getTableMetaData().getColumns()); // ne prend pas en compte la columne country
		
		Assertion.assertEquals(expectedTable, actualTable);
		
	}
}
