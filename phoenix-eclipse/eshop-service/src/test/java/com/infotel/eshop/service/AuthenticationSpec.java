package com.infotel.eshop.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import com.infotel.eshop.config.TestConfig;
import com.infotel.eshop.exception.AuthenticationException;
import com.infotel.eshop.model.Customer;
import com.infotel.eshop.model.Employee;

@RunWith(SpringRunner.class) @ContextConfiguration(classes=TestConfig.class)
public class AuthenticationSpec extends AbstractTest {

	@Autowired
	private UserService service;
	
	@Before
	public void init() throws Exception {
		cleanInsert();
	}
	
	@Test
	public void it_should_authenticate_customer_roger_rabbit() throws Exception {
		
		Customer cust = service.authenticateCustomer("roger@gmail.com", "secret");
		
		assertThat(cust)
		      .isNotNull()
		      //.extracting("firstName", "lastName")
		      .extracting(Customer::getUsername, Customer::getPassword, Customer::getFirstName, Customer::getLastName,
		    		  customer -> customer.getAddress().getStreet(), 
		    		  customer -> customer.getAddress().getPostcode(),
		    		  customer -> customer.getAddress().getCity(),
		    		  customer -> customer.getAddress().getCountry(),
		    		  customer -> customer.getDetail().getEmail(),
		    		  customer -> transformDateToString(customer.getDetail().getBirthDate()),
		    		  customer -> customer.getDetail().getPhone()) //syntaxe référence de nom : reference d'une methode de customer ici getFirstName
		      .containsExactly("roger@gmail.com", "secret", "Roger", "Rabbit",
		    		  		   "18 rue des Lilas", "75020", "Paris", "France",
		    		  		   "roger@gmail.com", "2001-05-30", "0601020304");
		
	}
	
	@Test
	public void it_should_authenticate_customer_emile_lampion() throws Exception {
		
		Customer cust = service.authenticateCustomer("emile@yahoo.com", "fleur");
		
		assertThat(cust)
		.isNotNull()
		//.extracting("firstName", "lastName")
		.extracting(Customer::getUsername, Customer::getPassword, Customer::getFirstName, Customer::getLastName,
				customer -> customer.getAddress().getStreet(), 
				customer -> customer.getAddress().getPostcode(),
				customer -> customer.getAddress().getCity(),
				customer -> customer.getAddress().getCountry(),
				customer -> customer.getDetail().getEmail(),
				customer -> transformDateToString(customer.getDetail().getBirthDate()),
				customer -> customer.getDetail().getPhone()) //syntaxe référence de nom : reference d'une methode de customer ici getFirstName
		.containsExactly("emile@yahoo.com", "fleur", "Émile", "Lampion",
				"18 rue des Lilas", "59200", "Tourcoing", "France",
				"emile@yahoo.com", "1985-04-26", "0624252627");
	}
	
	@Test
	public void it_should_authenticate_customer_amelie() throws Exception {
		
		Customer cust = service.authenticateCustomer("amelie@yahoo.fr", "cailloux");
		
		assertThat(cust)
		.isNotNull()
		//.extracting("firstName", "lastName")
		.extracting(Customer::getUsername, Customer::getPassword, Customer::getFirstName, Customer::getLastName,
				customer -> customer.getAddress().getStreet(), 
				customer -> customer.getAddress().getPostcode(),
				customer -> customer.getAddress().getCity(),
				customer -> customer.getAddress().getCountry(),
				customer -> customer.getDetail().getEmail(),
				customer -> transformDateToString(customer.getDetail().getBirthDate()),
				customer -> customer.getDetail().getPhone()) //syntaxe référence de nom : reference d'une methode de customer ici getFirstName
		.containsExactly("amelie@yahoo.fr", "cailloux", "Amelie", "Poulain",
				"12 rue de Montmartre", "75018", "Paris", "France",
				"amelie@yahoo.fr", "1990-10-17", "0714152123");
	}
	
	@Test
	public void it_should_authenticate_achille_when_address_and_detail_are_null() throws Exception {
		
		Customer cust = service.authenticateCustomer("achille@gmail.com", "fleche");
		
		assertThat(cust)
		      .isNotNull()
		      .extracting(Customer::getUsername, Customer::getPassword, Customer::getFirstName, Customer::getLastName,
		    		  customer -> customer.getAddress(),
		    		  customer -> customer.getDetail()) //syntaxe référence de nom : reference d'une methode de customer ici getFirstName
		      .containsExactly("achille@gmail.com", "fleche", "Achille", "Péléide",
		    		  		   null,
		    		  		   null);
		
	}

	@Test
	public void it_should_authenticate_when_address_is_null() throws Exception {
		
		Customer cust = service.authenticateCustomer("clement.lancien@gmail.com", "test");
		
		assertThat(cust)
		.isNotNull()
		.extracting(Customer::getUsername, Customer::getPassword, Customer::getFirstName, Customer::getLastName) //syntaxe référence de nom : reference d'une methode de customer ici getFirstName
		.containsExactly("clement.lancien@gmail.com", "test", "Clement", "Lancien");
		
		assertThat(cust.getAddress())
		.isNull();
	}
	
	@Test
	public void it_should_fail_to_authenticate_customer_roger_rabbit_when_password_is_incorrect() throws Exception {
		
		assertThatExceptionOfType(AuthenticationException.class)
			.isThrownBy(() -> service.authenticateCustomer("roger@gmail.com", "bad"))
			.withMessage("Echec de l'authentification : identifiant inconnu ou mot de passe incorrect")
			.withNoCause(); //exception purement applicative
		
	}
	
	@Test
	public void it_should_fail_to_authenticate_customer_emile_lampion_when_password_is_incorrect() throws Exception {
		
		assertThatExceptionOfType(AuthenticationException.class)
			.isThrownBy(() -> service.authenticateCustomer("emile@yahoo.com", "bad"))
			.withMessage("Echec de l'authentification : identifiant inconnu ou mot de passe incorrect")
			.withNoCause(); //exception purement applicative
		
	}
	
	@Test
	public void it_should_fail_to_authenticate_customer_amelie_when_password_is_incorrect() throws Exception {
		
		assertThatExceptionOfType(AuthenticationException.class)
		.isThrownBy(() -> service.authenticateCustomer("amelie@yahoo.fr", "bad"))
		.withMessage("Echec de l'authentification : identifiant inconnu ou mot de passe incorrect")
		.withNoCause(); //exception purement applicative
		
	}
	
	@Test
	public void it_should_fail_to_authenticate_customer_when_user_is_unknown() throws Exception {
		
		assertThatExceptionOfType(AuthenticationException.class)
			.isThrownBy(() -> service.authenticateCustomer("xxx", "bad"))
			.withMessage("Echec de l'authentification : identifiant inconnu ou mot de passe incorrect")
			.withNoCause(); //exception purement applicative
	}
	
	@Test
	public void it_should_fail_to_authenticate_customer_when_credentials_are_null() throws Exception { //credential ==> username + password
		
		assertThatExceptionOfType(AuthenticationException.class)
			.isThrownBy(() -> service.authenticateCustomer(null, null))
			.withMessage("Echec de l'authentification : identifiant inconnu ou mot de passe incorrect")
			.withNoCause(); //exception purement applicative
	}
	
	@Test
	public void it_should_fail_to_authenticate_customer_when_username_is_empty() throws Exception {
		
		assertThatExceptionOfType(AuthenticationException.class)
		.isThrownBy(() -> service.authenticateCustomer("", null))
		.withMessage("Echec de l'authentification : identifiant inconnu ou mot de passe incorrect")
		.withNoCause(); //exception purement applicative
	}
	
	@Test
	public void it_should_fail_to_authenticate_customer_when_password_is_null() throws Exception {
		
		assertThatExceptionOfType(AuthenticationException.class)
		.isThrownBy(() -> service.authenticateCustomer("roger@gmail.com", null))
		.withMessage("Echec de l'authentification : identifiant inconnu ou mot de passe incorrect")
		.withNoCause(); //exception purement applicative
	}
	
	@Test
	public void it_should_fail_to_authenticate_customer_roger_as_an_employee() throws Exception {
		
		assertThatExceptionOfType(AuthenticationException.class)
		.isThrownBy(() -> service.authenticateEmployee("roger@gmail.com", "secret"))
		.withMessage("Echec de l'authentification : l'utilisateur n'est pas un employe")
		.withNoCause(); //exception purement applicative
	}
	
	@Test
	public void it_should_fail_to_authenticate_customer_emile_as_an_employee() throws Exception {
		
		assertThatExceptionOfType(AuthenticationException.class)
		.isThrownBy(() -> service.authenticateEmployee("emile@yahoo.com", "fleur"))
		.withMessage("Echec de l'authentification : l'utilisateur n'est pas un employe")
		.withNoCause(); //exception purement applicative
	}
	
	@Test
	public void it_should_fail_to_authenticate_customer_amelie_as_an_employee() throws Exception {
		
		assertThatExceptionOfType(AuthenticationException.class)
		.isThrownBy(() -> service.authenticateEmployee("amelie@yahoo.fr", "cailloux"))
		.withMessage("Echec de l'authentification : l'utilisateur n'est pas un employe")
		.withNoCause(); //exception purement applicative
	}
	
	@Test
	public void it_should_authenticate_employee_francois() throws Exception {
		Employee emp = service.authenticateEmployee("francois@free.fr", "jaune");
		assertThat(emp)
	      .isNotNull()
	      .extracting(Employee::getFirstName, Employee::getLastName, Employee::getUsername, Employee::getPassword)
	      .containsExactly("François", "Pignon", "francois@free.fr", "jaune");
	}
	
	@Test
	public void it_should_authenticate_employee_smith() throws Exception {
		Employee emp = service.authenticateEmployee("smith@gmail.com", "algo");
		assertThat(emp)
		.isNotNull()
		.extracting(Employee::getFirstName, Employee::getLastName, Employee::getUsername, Employee::getPassword)
		.containsExactly("Smith", "Waterman", "smith@gmail.com", "algo");
	}
	
	@Test
	public void it_should_authenticate_employee_needleman() throws Exception {
		Employee emp = service.authenticateEmployee("needleman@yahoo.fr", "alignment");
		assertThat(emp)
		.isNotNull()
		.extracting(Employee::getFirstName, Employee::getLastName, Employee::getUsername, Employee::getPassword)
		.containsExactly("Needleman", "Wunsch", "needleman@yahoo.fr", "alignment");
	}
	
	@Test
	public void it_should_fail_to_authenticate_employee_francois_when_password_is_incorrect() throws Exception {
		
		assertThatExceptionOfType(AuthenticationException.class)
			.isThrownBy(() -> service.authenticateEmployee("francois@free.fr", "bad"))
			.withMessage("Echec de l'authentification : identifiant inconnu ou mot de passe incorrect")
			.withNoCause(); //exception purement applicative
		
	}
	
	@Test
	public void it_should_fail_to_authenticate_employee_smith_when_password_is_incorrect() throws Exception {
		
		assertThatExceptionOfType(AuthenticationException.class)
		.isThrownBy(() -> service.authenticateEmployee("smith@gmail.com", "bad"))
		.withMessage("Echec de l'authentification : identifiant inconnu ou mot de passe incorrect")
		.withNoCause(); //exception purement applicative
		
	}
	
	@Test
	public void it_should_fail_to_authenticate_employee_needleman_when_password_is_incorrect() throws Exception {
		
		assertThatExceptionOfType(AuthenticationException.class)
		.isThrownBy(() -> service.authenticateEmployee("needleman@yahoo.fr", "bad"))
		.withMessage("Echec de l'authentification : identifiant inconnu ou mot de passe incorrect")
		.withNoCause(); //exception purement applicative
		
	}
	
	@Test
	public void it_should_fail_to_authenticate_employee_when_user_is_unknown() throws Exception {
		
		assertThatExceptionOfType(AuthenticationException.class)
			.isThrownBy(() -> service.authenticateEmployee("xxx", "bad"))
			.withMessage("Echec de l'authentification : identifiant inconnu ou mot de passe incorrect")
			.withNoCause(); //exception purement applicative
	}
	
	@Test
	public void it_should_fail_to_authenticate_employee_when_credentials_are_null() throws Exception { //credential ==> username + password
		
		assertThatExceptionOfType(AuthenticationException.class)
			.isThrownBy(() -> service.authenticateEmployee(null, null))
			.withMessage("Echec de l'authentification : identifiant inconnu ou mot de passe incorrect")
			.withNoCause(); //exception purement applicative
	}
	
	@Test
	public void it_should_fail_to_authenticate_employee_when_username_is_empty() throws Exception {
		
		assertThatExceptionOfType(AuthenticationException.class)
		.isThrownBy(() -> service.authenticateEmployee("", null))
		.withMessage("Echec de l'authentification : identifiant inconnu ou mot de passe incorrect")
		.withNoCause(); //exception purement applicative
	}
	
	@Test
	public void it_should_fail_to_authenticate_employee_when_password_is_null() throws Exception {
		
		assertThatExceptionOfType(AuthenticationException.class)
		.isThrownBy(() -> service.authenticateEmployee("francois@free.fr", null))
		.withMessage("Echec de l'authentification : identifiant inconnu ou mot de passe incorrect")
		.withNoCause(); //exception purement applicative
	}
	
	@Test
	public void it_should_fail_to_authenticate_employee_francois_as_a_customer() throws Exception {
		
		assertThatExceptionOfType(AuthenticationException.class)
		.isThrownBy(() -> service.authenticateCustomer("francois@free.fr", "jaune"))
		.withMessage("Echec de l'authentification : l'utilisateur n'est pas un client")
		.withNoCause(); //exception purement applicative
	}
	
	@Test
	public void it_should_fail_to_authenticate_employee_smith_as_a_customer() throws Exception {
		
		assertThatExceptionOfType(AuthenticationException.class)
		.isThrownBy(() -> service.authenticateCustomer("smith@gmail.com", "algo"))
		.withMessage("Echec de l'authentification : l'utilisateur n'est pas un client")
		.withNoCause(); //exception purement applicative
	}
	
	@Test
	public void it_should_fail_to_authenticate_employee_needleman_as_a_customer() throws Exception {
		
		assertThatExceptionOfType(AuthenticationException.class)
		.isThrownBy(() -> service.authenticateCustomer("needleman@yahoo.fr", "alignment"))
		.withMessage("Echec de l'authentification : l'utilisateur n'est pas un client")
		.withNoCause(); //exception purement applicative
	}
	
}
