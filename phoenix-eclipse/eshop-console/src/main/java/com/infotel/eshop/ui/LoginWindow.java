package com.infotel.eshop.ui;


import com.infotel.eshop.exception.AuthenticationException;
import com.infotel.eshop.model.Customer;
import com.infotel.eshop.service.UserService;

public class LoginWindow extends Window {

	public LoginWindow() {
		super("login.title");
	}

	@Override
	protected void renderBody(Object... params) throws Exception {
		
		String username = inputString(getMessage("login.username") + " :");
		String password = inputString(getMessage("login.password") + " :");
		
		//UserService service = new UserServiceImpl();
		UserService service = SessionContext.Instance.getBean(UserService.class);
		
		
		Customer cust = null;
		render();
		try {
			cust = service.authenticateCustomer(username, password);
			//cust = submit(() -> service.authenticateCustomer(username, password));
			SessionContext.Instance.setCustomer(cust);
			render(getMessage("login.message.welcome", cust.getFirstName(), cust.getLastName())); //"Bienvenue " + cust.getFirstName() +  " " + cust.getLastName() + " !");
		} catch (AuthenticationException e) {
			//e.printStackTrace();
			render(getMessage("login.message.authFailed"));
		}
		render();
		if(params != null && params.length > 0 && cust != null) {
			navigate((String)params[0]);
		} else {
			navigate(UiConstants.HOME);
		}
	}
}