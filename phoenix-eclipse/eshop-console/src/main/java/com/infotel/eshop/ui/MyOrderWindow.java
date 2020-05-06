package com.infotel.eshop.ui;

import java.util.List;

import com.infotel.eshop.model.Order;
import com.infotel.eshop.model.OrderStatus;
import com.infotel.eshop.service.OrderService;

public class MyOrderWindow extends Window {
	
	public MyOrderWindow() {
		super("order.title");
	}

	@Override
	protected void renderBody(Object... params) throws Exception {
		//OrderService service = new OrderServiceImpl();
		OrderService service = getBean(OrderService.class);
		String username = SessionContext.Instance.getCustomer().getUsername();
		
		List<Order> orders = service.findOrdersByCustomer(username);
		//List<Order> orders = submit(() -> service.findOrdersByCustomer(username));
//		for (Order order : orders) {
//			render(getMessage("order.render.order", order.getId(), statusToLabel(order.getStatus())));
//			for (OrderLine line : order.getLines()) {
//				render(getMessage("order.render.orderline", line.getProduct().getName(),line.getQuantity()));
////				render("\t- " + line.getProduct().getName() + " - " + line.getQuantity());
//			}
//		}
		
//		orders.stream()
//		      .forEach(order -> {
//		    	  render(order.getId() + " - " + statusToLabel(order.getStatus()));
//		    	  order.getLines()
//		    	       .stream()
//		    	       .forEach(line -> render("\t- " + line.getProduct().getName() + " - " + line.getQuantity()));
//		      });
		
		orders.stream()
	      .forEach(order -> {
	    	  render(getMessage("order.render.order", order.getId(), statusToLabel(order.getStatus())));
	    	  order.getLines()
	    	       .stream()
	    	       .forEach(line -> render(getMessage("order.render.orderline", line.getProduct().getName(), line.getQuantity())));
	      });

		
		if (orders.isEmpty()) {
			render(getMessage("order.warning.noOrder"));
		}
		navigate(UiConstants.HOME);
	}

	private String statusToLabel(OrderStatus status) {
		switch (status) {
		case Pending:
			return "En attente";
		case Processing:
			return "En cours";
		case Shipped:
			return "Envoyée";
		case Cancelled:
			return "Annulée";
		default:
			return "";
		}
	}
}
