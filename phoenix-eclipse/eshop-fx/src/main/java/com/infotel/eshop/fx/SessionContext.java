package com.infotel.eshop.fx;

import com.infotel.eshop.fx.model.User;

import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public enum SessionContext {
	Instance;
	
	private Stage stage;
	private User user;

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
		stage.setTitle("Eshop - " + user.getFirstName() + " " + user.getLastName());
	}

	public void setStage(Stage stage) {
		this.stage = stage;
	}
	
	public void navigate(String target) {
		Parent container = null;
		
		switch(target) {
		case "login":
			container = new LoginWindow().build();
			break;
			
		case "order-list":
			container = new OrderListWindow().build();
			break;
		}
		
		Scene scene = new Scene(container);
		stage.setScene(scene);
	}
}
