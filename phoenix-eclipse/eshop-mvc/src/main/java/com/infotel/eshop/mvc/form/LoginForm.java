package com.infotel.eshop.mvc.form;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

public class LoginForm {

	@NotBlank(message="L'identifiant est obligatoire")
	@Email(message="Veuillez saisir une adresse mail valide")
	private String username;
	
	@NotBlank(message="Le mot de passe est obligatoire")
	//@Size(min=3, max=20, message="Le mot de passe doit posséder entre 3 et 20 caractères")
	private String password;
	
	@Override
	public String toString() {
		return "LoginForm [username=" + username + ", password=" + password + "]";
	}

	public String getUsername() {
		return username;
	}

	public String getPassword() {
		return password;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
}
