package com.infotel.eshop.fx;

import javafx.application.Application;
import javafx.stage.Stage;

public class EShopApp extends Application {

	@Override
	public void start(Stage stage) throws Exception {
		
//		Label usernameLabel = new Label("Identifiant");
//		TextField usernameTextField = new TextField();
//		
//		Label passwordLabel = new Label("Mot de passe");
//		PasswordField passwordTextField = new PasswordField();
//		
//		Button signInButton = new Button("Se connecter");
//		signInButton.setOnAction(event -> authenticate(usernameTextField.getText(), passwordTextField.getText()));
//		
////		new EventHandler<ActionEvent>() {
////			@Override
////			public void handle(ActionEvent event) {
////				System.out.println("Bouton cliqué...");
////			}
////		});
//		
//		
//		GridPane formPane = new GridPane();
//		formPane.add(usernameLabel, 0, 0);
//		formPane.add(usernameTextField, 1, 0);
//		formPane.add(passwordLabel, 0, 1);
//		formPane.add(passwordTextField, 1, 1);
//		formPane.add(signInButton, 0, 2);
//		
//		formPane.setHgap(10);
//		formPane.setVgap(10);
//		formPane.setPadding(new Insets(50));
//		//formPane.setGridLinesVisible(true);
//		
//		GridPane.setColumnSpan(signInButton, 2);
//		GridPane.setHalignment(signInButton, HPos.CENTER);
		
		SessionContext.Instance.setStage(stage);
		
//		Parent container = new LoginWindow().build();
//		Scene scene = new Scene(container);
//		stage.setScene(scene);
		
		SessionContext.Instance.navigate("login");
		
		stage.setTitle("EShop App");
		stage.show();
		
	}
	
//	private void authenticate(String username, String password) {
//		System.out.println("Demande authentification : " + username + " / " + password);
//	}

}
