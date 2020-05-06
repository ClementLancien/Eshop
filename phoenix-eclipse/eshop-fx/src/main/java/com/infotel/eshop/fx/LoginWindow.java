package com.infotel.eshop.fx;

import javax.security.sasl.AuthenticationException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.infotel.eshop.fx.model.User;
import com.infotel.eshop.fx.service.UserService;

import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

public class LoginWindow {
	
	private final static Logger log = LogManager.getLogger(LoginWindow.class);
	
	private Label usernameLabel;
	private TextField usernameTextField;
	private Label passwordLabel;
	private PasswordField passwordTextField;
	private Button signInButton;
	private GridPane formPane;
	
	private UserService service;

	public LoginWindow() {
		super();
		this.service = new UserService();
	}

	public Parent build() {
		usernameLabel = new Label("Identifiant");
		usernameTextField = new TextField();
		
		passwordLabel = new Label("Mot de passe");
		passwordTextField = new PasswordField();
		
		signInButton = new Button("Se connecter");
		signInButton.setDefaultButton(true);
		signInButton.setOnAction(event -> authenticate());
		
		
		formPane = new GridPane();
		formPane.add(usernameLabel, 0, 0);
		formPane.add(usernameTextField, 1, 0);
		formPane.add(passwordLabel, 0, 1);
		formPane.add(passwordTextField, 1, 1);
		formPane.add(signInButton, 0, 2);
		
		formPane.setHgap(10);
		formPane.setVgap(10);
		formPane.setPadding(new Insets(50));
		//formPane.setGridLinesVisible(true);
		
		GridPane.setColumnSpan(signInButton, 2);
		GridPane.setHalignment(signInButton, HPos.CENTER);
		
		return formPane;
	}
	
	private void authenticate() {
		String username = usernameTextField.getText();
		String password = passwordTextField.getText();
		
		if (log.isDebugEnabled()) {
			log.debug("Demande authentification : " + username + " / " + password);
		}
		//System.out.println("Demande authentification : " + username + " / " + password);
		
		try {
			User user = service.authenticate(username, password);
			SessionContext.Instance.setUser(user);
			SessionContext.Instance.navigate("order-list");
			
			if (log.isDebugEnabled()) {
				log.debug("user : " + SessionContext.Instance.getUser());
			}
			
		} catch (AuthenticationException e) {
			log.error("Echec de l'authentification", e);
			
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Authentification");
			alert.setHeaderText("Echec de l'authentification");
			alert.setContentText("Votre identifiant ou mot de passe incorrect. Veuillez essayer à nouveau.");
			
			alert.showAndWait();
		}
	}
}
