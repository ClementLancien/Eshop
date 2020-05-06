package com.infotel.eshop.service;

import com.infotel.eshop.exception.AuthenticationException;
import com.infotel.eshop.exception.EShopException;
import com.infotel.eshop.exception.RegisterException;
import com.infotel.eshop.model.Customer;
import com.infotel.eshop.model.Employee;

public interface UserService {

	Customer authenticateCustomer(String username, String password) throws AuthenticationException, EShopException;
	
	Employee authenticateEmployee(String username, String password) throws AuthenticationException, EShopException;

	void registerCustomer(Customer customer) throws EShopException, RegisterException;

}