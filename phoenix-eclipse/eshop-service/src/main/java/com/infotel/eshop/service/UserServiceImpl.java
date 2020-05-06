package com.infotel.eshop.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.infotel.eshop.dao.UserDao;
import com.infotel.eshop.exception.AuthenticationException;
import com.infotel.eshop.exception.EShopException;
import com.infotel.eshop.exception.RegisterException;
import com.infotel.eshop.model.Customer;
import com.infotel.eshop.model.Employee;
import com.infotel.eshop.model.User;


//@Component("userService")
@Service @Transactional//("userService")
public class UserServiceImpl implements UserService, InitializingBean, DisposableBean {
	
	private final static Logger log = LogManager.getLogger(UserServiceImpl.class);
	
	// pr passer a spring
	//@Autowired// @Qualifier("userDAO") // injection qualifier nom du bean qu'on injecte
	private UserDao userDao; // = new UserDaoJpa();
	
	@Override
	public void afterPropertiesSet() throws Exception {
		log.info("Le service est initialisé");
	}
	
	//@Autowired
	public UserServiceImpl(UserDao userDao) {
		super();
		this.userDao = userDao;
	}

	@Override
	public void destroy() throws Exception {
		log.info("Le service va se détruire");
	}
	
//	public void setUserDao(UserDao userDao) {
//		this.userDao = userDao;
//	}
	
	private User authenticate(String username, String password) throws AuthenticationException, EShopException {
//		UserDao userDao = new UserDaoMemory();
		//UserDao userDao = new UserDaoJdbc();
		//UserDao userDao = new UserDaoJpa();
		User user = userDao.findByUsername(username);
		
		//échec de l'authentification
		if (user == null || !user.getPassword().equals(password)) {
			throw new AuthenticationException("Echec de l'authentification : identifiant inconnu ou mot de passe incorrect");
		}
		
		//authentification Ok
		return user;
	}
	
	@Override
	public Customer authenticateCustomer(String username, String password) throws AuthenticationException, EShopException {
		
		//Code ok
		User user = authenticate(username, password);
		//user.getPassword()
		if (user instanceof Customer) {
			Customer cust = (Customer)user;
			return cust;
		}
		
		throw new AuthenticationException("Echec de l'authentification : l'utilisateur n'est pas un client");
	}
	
	@Override
	public Employee authenticateEmployee(String username, String password) throws AuthenticationException, EShopException {
		
		//Code ok
		User user = authenticate(username, password);
		//user.getPassword()
		if (user instanceof Employee) {
			Employee emp = (Employee)user;
			return emp;
		}
		
		throw new AuthenticationException("Echec de l'authentification : l'utilisateur n'est pas un employe");
	}
	
	@Override
	public void registerCustomer(Customer customer) throws EShopException, RegisterException {
		if(customer.getUsername() == null || !customer.getUsername().contains("@")) {
			throw new RegisterException("Echec de l'inscription : l'identifiant n'est pas un email");
			//return false;
		}
		
		if(customer.getPassword() == null || customer.getPassword().isEmpty()) {
			//return false;
		}
		
		
//		UserDao userDao = new UserDaoMemory();
		//UserDao userDao = new UserDaoJdbc();
		//UserDao userDao = new UserDaoJpa();
		if (userDao.findByUsername(customer.getUsername()) != null) {
			throw new RegisterException("Echec de l'inscription : utilisateur existant");
			//return false;
		}
		
		userDao.create(customer);
		//return true;
	}


}