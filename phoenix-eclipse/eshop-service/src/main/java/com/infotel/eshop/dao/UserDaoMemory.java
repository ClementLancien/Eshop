package com.infotel.eshop.dao;

import com.infotel.eshop.model.Customer;
import com.infotel.eshop.model.Employee;
import com.infotel.eshop.model.User;

public class UserDaoMemory implements UserDao {
	
	private static User[] userTable = { // normalement utiliser un singleton plutot que static car en vrai static génère des blocages
			new Customer("roger@gmail.com", "secret", "Roger", "Rabbit"),
			new Customer("amelie@yahoo.fr", "cailloux", "Amélie", "Poulain"),
			new Employee("emile@hotmail.com","fleur")
	};
	
	@Override
	public User findByUsername(String username) {
		
		//cas d'utilistaion du polymorphisme. QUand on rajoute des nouveaux types on ne casse pas le code
		for(User user : userTable) {
			if(user.getUsername().equals(username)) {
				return user;
			}
		}
		
		return null;
	}
	
	@Override
	public void create(User user) {
		
		User[] userTableNew = new User[userTable.length + 1];
		
		//remplir la nouvelle table
		for (int i = 0; i < userTable.length; i++) {
			userTableNew[i] = userTable[i];
		}
		userTableNew[userTableNew.length - 1] = user;
		userTable = userTableNew;
	}
}