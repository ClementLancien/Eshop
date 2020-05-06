package com.infotel.eshop.fx;

import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;

public class DetailPaneHeader {
	private Label commandeName;
	private Label date;
	private Label customerName;
	
	private GridPane formPane;
	
	public Parent build() {
		commandeName = new Label("Numéro de commande");
		date = new Label("date de la commande");
		customerName = new Label("Nom du client");
		
		formPane = new GridPane();
		formPane.add(commandeName, 0, 0);
		formPane.add(date, 0, 1);
		formPane.add(customerName, 0, 2);

		formPane.setHgap(10);
		formPane.setVgap(10);
		formPane.setPadding(new Insets(50));
		
		return formPane;
	}
}
