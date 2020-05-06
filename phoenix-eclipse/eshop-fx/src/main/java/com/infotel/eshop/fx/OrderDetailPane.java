package com.infotel.eshop.fx;

import java.time.format.DateTimeFormatter;

import com.infotel.eshop.fx.model.Order;
import com.infotel.eshop.fx.model.OrderLine;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

public class OrderDetailPane {
	private VBox container;
	
	// entête
	private AnchorPane headerPane;
	private Label orderNumberLabel;
	private Label orderNumberLabelValue;
	private Label dateLabel;
	private Label dateLabelValue;
	
	// liste
	private ListView<OrderLine> lineListView;
	private ObservableList<OrderLine> lines;
	
	public Parent build() {
		
		// entête
		orderNumberLabel = new Label("Numéro :");
		orderNumberLabel.setStyle("-fx-font-weight: bold");
		orderNumberLabelValue = new Label();
		
		AnchorPane.setTopAnchor(orderNumberLabel, 10.0);
		AnchorPane.setLeftAnchor(orderNumberLabel, 10.0);
		
		AnchorPane.setTopAnchor(orderNumberLabelValue, 10.0);
		AnchorPane.setLeftAnchor(orderNumberLabelValue, 70.0);
		
		dateLabel = new Label("Date :");
		dateLabel.setStyle("-fx-font-weight: bold");
		dateLabelValue = new Label();
		
		AnchorPane.setTopAnchor(dateLabel, 40.0);
		AnchorPane.setLeftAnchor(dateLabel, 10.0);
		
		AnchorPane.setTopAnchor(dateLabelValue, 40.0);
		AnchorPane.setLeftAnchor(dateLabelValue, 50.0);
		
		headerPane = new AnchorPane(
				orderNumberLabel,
				orderNumberLabelValue,
				dateLabel,
				dateLabelValue
		);
		headerPane.setPadding(new Insets(0, 0, 10, 0));
		
		// Liste des lignes de commandes
		lineListView = new ListView<OrderLine>();
		lineListView.setPlaceholder(new Label("<Pas de ligne de commandes>"));
		lineListView.setPrefWidth(300);
//		lineListView.setMaxWidth(800);
		lineListView.setCellFactory(view -> new OrderLineCell());
		
		lines = FXCollections.observableArrayList();
		lineListView.setItems(lines);
		
		container = new VBox(
				headerPane,
				lineListView
		);
		return container;
	}
	
	public void update(Order order) {
		orderNumberLabelValue.setText(order.getOrderNumber());
		
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		dateLabelValue.setText(order.getDateTime().format(dtf));
		
		lines.clear();
		lines.addAll(order.getLines());
	}

}