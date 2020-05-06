package com.infotel.eshop.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

import java.util.List;

import org.dbunit.Assertion;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.ITable;
import org.dbunit.dataset.filter.DefaultColumnFilter;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import com.infotel.eshop.config.TestConfig;
import com.infotel.eshop.exception.EShopException;
import com.infotel.eshop.model.Book;
import com.infotel.eshop.model.Customer;
import com.infotel.eshop.model.Movie;
import com.infotel.eshop.model.Order;
import com.infotel.eshop.model.OrderStatus;
import com.infotel.eshop.model.Product;

@RunWith(SpringRunner.class) @ContextConfiguration(classes=TestConfig.class)
public class OrderSpec extends AbstractTest {
	
	@Autowired
	private OrderService service;
	
	@Before
	public void init() throws Exception {
		cleanInsert();
	}
	
	@Test 
	public void it_should_place_an_order_from_roger() throws Exception {
		//arrange
		Customer cust = new Customer();
		cust.setUsername("roger@gmail.com");
		
		Product p1 = new Book();
		p1.setId(1);
		
		
		Product p2 = new Movie();
		p2.setId(28);
		
		Order order = new Order();
		order.addLine(p1, 1);
		order.addLine(p2, 2);
		
		order.setCustomer(cust);
		
		//act
		service.placeOrder(order);
		
		//assert
		
		ITable actualTable = loadActualTable("purchase_order");
		IDataSet expectedDataSet = getReplacement(loadDataSet("src/test/resources/datasets/place_order/place_order_roger.xml"));
		ITable expectedTable = expectedDataSet.getTable("purchase_order");
		
		actualTable = DefaultColumnFilter.includedColumnsTable(actualTable,
				expectedTable.getTableMetaData().getColumns()); // ne prend pas en compte la columne country
		
		Assertion.assertEquals(expectedTable, actualTable);
		
		actualTable = loadActualTable("order_line");
		expectedDataSet = getReplacement(loadDataSet("src/test/resources/datasets/place_order/place_order_roger.xml"));
		expectedTable = expectedDataSet.getTable("order_line");
		
		actualTable = DefaultColumnFilter.includedColumnsTable(actualTable,
				expectedTable.getTableMetaData().getColumns()); // ne prend pas en compte la columne country
		
		Assertion.assertEquals(expectedTable, actualTable);
	}
	
	@Test //@Ignore
	public void it_should_find_amelie_order() throws Exception {
		//arrange
		
		service.findOrdersByCustomer("amelie@yahoo.fr");
		
		//act
		
		//assert
		
		ITable actualTable = loadActualTable("purchase_order");
		IDataSet expectedDataSet = getReplacement(loadDataSet("src/test/resources/datasets/find_order/find_order_of_amelie.xml"));
		ITable expectedTable = expectedDataSet.getTable("purchase_order");
		
		actualTable = DefaultColumnFilter.includedColumnsTable(actualTable,
				expectedTable.getTableMetaData().getColumns()); // ne prend pas en compte la columne country
		
		Assertion.assertEquals(expectedTable, actualTable);
		
		actualTable = loadActualTable("order_line");
		expectedDataSet = getReplacement(loadDataSet("src/test/resources/datasets/find_order/find_order_of_amelie.xml"));
		expectedTable = expectedDataSet.getTable("order_line");
		
		actualTable = DefaultColumnFilter.includedColumnsTable(actualTable,
				expectedTable.getTableMetaData().getColumns()); // ne prend pas en compte la columne country
		
		Assertion.assertEquals(expectedTable, actualTable);
	}

	
	@Test @Ignore
	public void it_should_failed_to_create_order_when_orderline_is_incorrect() throws Exception {
		//Arrange
		Order order = new Order();
		//order.setId(1);
		//order.setOrderNumber("CDE20-0003");
		//order.setDateTime(LocalDateTime.parse("2020-01-14T10:05:00"));
		//order.setStatus(OrderStatus.Pending);
		
		Customer cust = new Customer();
		//cust.setFirstName("Roger");
		//cust.setLastName("Rabbit");
		cust.setUsername("it_should_failed");
		//cust.setPassword("secret");
		
		order.setCustomer(cust);
		
		Product p1 = new Product();
		p1.setId(1718);
		p1.setName("Les trésors de Tintin : 22 fac-similés rares extraits des archives d'Hergé");
		p1.setDescription("La genèse des aventures de Tintin album après album.");
		p1.setPrice(34.0);
		
		order.addLine(p1, 3);
		
		Product p2 = new Product();
		p2.setId(50);
		p2.setName("Séries TV cultes");
		p2.setDescription("Coffret découverte des séries cultes Universal contenant le 1er DVD de "
						+ "chaque série. Contient le premier disque des séries : K 2000- Agence tous "
						+ "risques- 2 flics à Miami- Magnum- Columbo- Supercopter- Les têtes brûlées- "
						+ "Super Jaimie- L'homme qui valait 3 milliards.");
		p2.setPrice(11.0);
		
		order.addLine(p2, 4);
		
		//Assert
		
		assertThatExceptionOfType(EShopException.class)
			.isThrownBy(() -> service.placeOrder(order))
			.withMessage("Problème accès base de données");
	}
	
	@Test
	public void it_should_find_orders_to_process() throws Exception {
		List<Order> orders = service.findOrdersToProcess();
		
		assertThat(orders)
		.hasSize(2);
		
		assertThat(orders.get(0))
			.isNotNull()
			.extracting(Order::getOrderNumber,Order::getStatus, o -> o.getCustomer().getUsername())
			.containsExactly("CDE20-0001", OrderStatus.Pending, "amelie@yahoo.fr");
		
		assertThat(orders.get(1))
		.isNotNull()
		.extracting(Order::getOrderNumber,Order::getStatus, o -> o.getCustomer().getUsername())
		.containsExactly("CDE20-0002", OrderStatus.Pending, "emile@yahoo.com");
	}
}
