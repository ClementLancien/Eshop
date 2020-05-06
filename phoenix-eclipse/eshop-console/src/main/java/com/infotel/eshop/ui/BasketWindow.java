package com.infotel.eshop.ui;

import java.util.List;

import com.infotel.eshop.model.Order;
import com.infotel.eshop.model.OrderLine;
import com.infotel.eshop.service.OrderService;

public class BasketWindow extends Window {

	public BasketWindow() {
		super("basket.title");
	}

	@Override
	protected void renderBody(Object... params) throws Exception {
		
		List<OrderLine> basket = SessionContext.Instance.getBasket();
		for (int i = 0; i < basket.size(); i++) {
			OrderLine line = basket.get(i);
			render(getMessage("basket.render.basket", (i+1), line.getQuantity(), line.getProduct().getName()));
//			render("[" + (i+1) + "] " + line.getQuantity() + " - " + line.getProduct().getName());
		}
		
		if (basket.isEmpty()) {
			render(getMessage("basket.render.isEmpty"));
		}
		
		// menu
		render();
		render("1. " + getMessage("basket.menu.order")); // on demande à l'utilisateur de passer commande ou de retourner à la page d'accueil
		render("2. " + getMessage("basket.menu.goBack"));
		render();
		
		
		String choice = inputString(getMessage("basket.input.choice"));
		switch (choice) {
		case "1":
			if (basket.isEmpty()) {
				render();
				render(getMessage("basket.render.addProductToBasket"));
				navigate(UiConstants.HOME);
			}
			
			if (SessionContext.Instance.getCustomer() != null) {
				
				// passer la commande
				//OrderService service = new OrderServiceImpl();
				OrderService service = getBean(OrderService.class);
//				List<OrderLine> lines = new ArrayList<>();
//				lines.addAll(SessionContext.Instance.getBasket());
//				for (OrderLine line : SessionContext.Instance.getBasket()) {
//					lines.add(line);
//				}
				
				Order order = new Order();
				//order.setLines(lines);
				for (OrderLine line : SessionContext.Instance.getBasket()) {
					order.addLine(line.getProduct(), line.getQuantity());
				}
				order.setCustomer(SessionContext.Instance.getCustomer());
				
				service.placeOrder(order);
				SessionContext.Instance.clearBasket();
				
				render();
				render(getMessage("basket.warning.order"));
				navigate(UiConstants.HOME);
				render();
				
			} else {
				render();
				render(getMessage("basket.alert.login"));
				navigate(UiConstants.LOGIN, UiConstants.BASKET);
			}
			break;
			
		case "2":
			navigate(UiConstants.HOME); 
			break;
		}
	}

}
