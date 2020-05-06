package com.infotel.eshop.fx;

import com.infotel.eshop.fx.model.Order;

import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.layout.AnchorPane;

public class OrderCell extends ListCell<Order> {

	private AnchorPane container;
	private Label orderNumberLabel;
	private Label customerLabel;
	
	public OrderCell() {
		super();
		initCell();
	}

	private void initCell() {
		orderNumberLabel = new Label();
		customerLabel = new Label();
		
		orderNumberLabel.setStyle("-fx-font-weight: bold");
		
		AnchorPane.setTopAnchor(orderNumberLabel, 10.0);
		AnchorPane.setLeftAnchor(orderNumberLabel, 10.0);
		AnchorPane.setBottomAnchor(orderNumberLabel, 10.0);
		
		AnchorPane.setTopAnchor(customerLabel, 10.0);
		AnchorPane.setLeftAnchor(customerLabel, 100.0);
		//AnchorPane.setBottomAnchor(customerLabel, 10.0); //inutile
		
		container = new AnchorPane(
				orderNumberLabel,
				customerLabel
		);
	}
	
	@Override
	protected void updateItem(Order item, boolean empty) {
		super.updateItem(item, empty);
		
		if (empty) {
			setGraphic(null);
			//setText("");
		} else {
			orderNumberLabel.setText(item.getOrderNumber());
			customerLabel.setText(item.getCustomer().getName());
			setGraphic(container);
			//setText(item.getOrderNumber() + " - " + item.getCustomer().getName());
		}
	}
}
