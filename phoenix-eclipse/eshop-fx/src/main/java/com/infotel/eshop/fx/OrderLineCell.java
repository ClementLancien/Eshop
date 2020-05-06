package com.infotel.eshop.fx;

import com.infotel.eshop.fx.model.OrderLine;

import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.layout.AnchorPane;

public class OrderLineCell extends ListCell<OrderLine>{
	
	private AnchorPane container;
	private Label productLabel;
	private Label quantityLabel;
	
	public OrderLineCell() {
		super();
		initCell();
	}

	private void initCell() {
		productLabel = new Label();
		quantityLabel = new Label();
		
		productLabel.setStyle("-fx-font-weight: bold");
		
		AnchorPane.setTopAnchor(productLabel, 10.0);
		AnchorPane.setLeftAnchor(productLabel, 10.0);
		AnchorPane.setBottomAnchor(productLabel, 10.0);
		
		AnchorPane.setTopAnchor(quantityLabel, 10.0);
		AnchorPane.setRightAnchor(quantityLabel, 10.0);
		//AnchorPane.setBottomAnchor(customerLabel, 10.0); //inutile
		
		container = new AnchorPane(
				productLabel,
				quantityLabel
		);
	}
	
	@Override
	protected void updateItem(OrderLine item, boolean empty) {
		super.updateItem(item, empty);
		
		if (empty) {
			setGraphic(null);
			//setText("");
		} else {
			
			productLabel.setText(item.getProduct().getName());
			quantityLabel.setText(Integer.toString(item.getQuantity()));
			setGraphic(container);
		}
	}
	
}
