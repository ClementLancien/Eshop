package com.infotel.eshop.fx;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.infotel.eshop.fx.model.Order;
import com.infotel.eshop.fx.service.OrderService;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;

public class OrderListWindow {
	
	private final static Logger log = LogManager.getLogger(OrderListWindow.class);

	//layout
	private StackPane container;
	private HBox mainPane;
	
	//Partie liste
	private ListView<Order> listView;
	
	//Partie liste
	private OrderDetailPane detailPane;
	
	private ObservableList<Order> orders;
	
	private OrderService service;
	
	public OrderListWindow() {
		super();
		this.service = new OrderService();
	}

	public Parent build() {
		service = new OrderService();
		
		listView = new ListView<>();
//		container = new StackPane(listView);
//		StackPane.setMargin(listView, new Insets(10));
		
		listView.setPrefWidth(400);
		listView.setPlaceholder(new Label("<Pas de résultat>"));
		listView.setCellFactory(view -> new OrderCell());
		
		orders = FXCollections.observableArrayList();
		listView.setItems(orders);
		
		listView.getSelectionModel()
				.selectedItemProperty()
				.addListener((observable, oldValue, newValue) -> orderSelected(newValue));
		
		List<Order> orders = service.findOrdersToProcess();
		this.orders.addAll(orders);
		
		//partie détail
		detailPane = new OrderDetailPane();
		
		//partie layout
		mainPane = new HBox(listView, detailPane.build());
		mainPane.setSpacing(10);
		
		container = new StackPane(mainPane);
		StackPane.setMargin(mainPane, new Insets(10));
				
		return container;
	}

	private void orderSelected(Order order) {
		if (log.isDebugEnabled()) {
			log.debug("Commande sélectionnée : " + order);
		}
		
		detailPane.update(order);
	}
	
}