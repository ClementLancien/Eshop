package com.infotel.eshop.service;

import java.text.MessageFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.infotel.eshop.dao.ItemCounterDao;
import com.infotel.eshop.dao.OrderDao;
import com.infotel.eshop.exception.EShopException;
import com.infotel.eshop.model.ItemCounter;
import com.infotel.eshop.model.Order;
import com.infotel.eshop.model.OrderStatus;

@Service @Transactional
public class OrderServiceImpl implements OrderService {

	private static final Logger log = LogManager.getLogger(OrderServiceImpl.class);
	
	//@Autowired
	private OrderDao orderDao;
	//private OrderDao orderDao = new OrderDaoJpa();
	
	//@Autowired
	private ItemCounterDao itemCounterDao;
	
	//private ProductDao productDao;
	
	//@Autowired
	public OrderServiceImpl(OrderDao orderDao, ItemCounterDao itemCounterDao) {
		super();
		this.orderDao = orderDao;
		this.itemCounterDao = itemCounterDao;
	}

	@Override
	public void placeOrder(Order order) throws EShopException {
		if (log.isDebugEnabled()) {
			log.debug("Enregistrer la commander " + order.getId());
		}
		order.setStatus(OrderStatus.Pending);
//		order.setOrderNumber("CDE19-0001"); 
		order.setOrderNumber(generatedOrderNumber());
		order.setDateTime(LocalDateTime.now().withSecond(0).withNano(0));
//		OrderDao orderDao = new OrderDaoMemory();
		
		//OrderDao orderDao = new OrderDaoJdbc();
		//OrderDao orderDao = new OrderDaoJpa();
		orderDao.create(order);
		
		// inserer les lignes de commandes
		//fait avec jpa
//		OrderLineDao orderLineDao = new OrderLineDaoJdbc();
//		for (int i = 0; i < order.getLines().size(); i++) {
//			orderLineDao.create(order.getLines().get(i), i);
//		}
	}
	
	// pattern CDEYY-0000
	private String generatedOrderNumber() throws EShopException {
		String orderNum = "CDE{0}-{1,number,0000}"; // ne pas mettre d'espace
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yy");
		String year = LocalDate.now().format(dtf);
		
		//ItemCounterDao itemCounterDao = new ItemCounterDaoJdbc();
		//ItemCounterDao itemCounterDao = new ItemCounterDaoJpa();
		ItemCounter counter = itemCounterDao.findByID("order", year);
		
		if (counter == null) {
			counter = new ItemCounter();
			counter.setItem("order");
			counter.setSubset(year);
			counter.setNextValue(1);
			itemCounterDao.create(counter);
		} else {
			counter.increment();
			itemCounterDao.update(counter);
		}
		
		String num = MessageFormat.format(orderNum, year, counter.getNextValue());
		
		if (log.isDebugEnabled()) {
			log.debug("Numéro de commande généré : " + num);
		}
		
		return num;
	}
	
	@Override
	public List<Order> findOrdersByCustomer(String username) throws EShopException {
//		return new Order[0];
//		OrderDao orderDao = new OrderDaoMemory();
//		return orderDao.findByCustomer(username);
//		OrderDao orderDao = new OrderDaoJdbc();
//		OrderLineDao orderLineDao = new OrderLineDaoJdbc();
		//ProductDao productDao = new ProductDaoJdbc();
		//ProductDao productDao = new ProductDaoJpa();
		
		//OrderDao orderDao = new OrderDaoJpa();
		List<Order> orders = orderDao.findByCustomer(username);
		
//		for(Order order : orders) {
//			List<OrderLine> lines = orderLineDao.findByOrder(order.getId());
//			order.setLines(lines);
//			
//			for (OrderLine line : lines) {
//				line.setOrder(order);
//				
//				Product p = productDao.findById(line.getProduct().getId());
//				line.setProduct(p);
//			}
//		}
		return orders;
	}

	@Override
	public List<Order> findOrdersToProcess() { // status pending ou processing
		return orderDao.findWithStatus(OrderStatus.Pending, OrderStatus.Processing);
	}

}
