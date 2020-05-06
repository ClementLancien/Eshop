package com.infotel.eshop.mapper;

import com.infotel.eshop.dto.CustomerDto;
import com.infotel.eshop.dto.RegisterDto;
import com.infotel.eshop.model.Customer;

public interface UserMapper {

//	static CustomerDto customerToCustomerDto(Customer customer) {
//		CustomerDto dto = new CustomerDto();
//		dto.setUsername(customer.getUsername());
//		dto.setFirstName(customer.getFirstName());
//		dto.setLastName(customer.getLastName());
//		
//		return dto;
//	}
	
	static Customer registerDtotoCustomer(RegisterDto dto) {
		Customer cust = new Customer();
		cust.setUsername(dto.getUsername());
		cust.setPassword(dto.getPassword());
		cust.setFirstName(dto.getFirstName());
		cust.setLastName(dto.getLastName());
		
		return cust;
	}
	
	static CustomerDto customerToCustomerDto(Customer cust) {
		CustomerDto dto = new CustomerDto();
		dto.setUsername(cust.getUsername());
		dto.setFirstName(cust.getFirstName());
		dto.setLastName(cust.getLastName());
		
		return dto;
	}
	
	static Customer customerDtoToCustomer(String username) {
		Customer cust = new Customer();
		cust.setUsername(username);
		
		return cust;
	}
	
}
