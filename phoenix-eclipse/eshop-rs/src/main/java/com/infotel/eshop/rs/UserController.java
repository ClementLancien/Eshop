package com.infotel.eshop.rs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.infotel.eshop.dto.CustomerDto;
import com.infotel.eshop.dto.LoginDto;
import com.infotel.eshop.dto.RegisterDto;
import com.infotel.eshop.exception.AuthenticationException;
import com.infotel.eshop.exception.EShopException;
import com.infotel.eshop.exception.RegisterException;
import com.infotel.eshop.mapper.UserMapper;
import com.infotel.eshop.model.Customer;
import com.infotel.eshop.service.UserService;

@RestController @RequestMapping("/users")
public class UserController {
	
	@Autowired
	private UserService service;
	
	@PutMapping("/register")
	public ResponseEntity<Void> register(@RequestBody RegisterDto dto) {
		Customer cust = UserMapper.registerDtotoCustomer(dto);
		try {
			service.registerCustomer(cust);
			return new ResponseEntity<Void>(HttpStatus.OK);
		} catch (EShopException | RegisterException e) {
			return new ResponseEntity<Void>(HttpStatus.CONFLICT); // error 409
		}
	}
	
	@PostMapping("/login")
	public ResponseEntity<CustomerDto> login(@RequestBody LoginDto dto) {
		System.err.println(dto.getUsername());
		try {
			Customer cust = service.authenticateCustomer(dto.getUsername(), dto.getPassword());
			return new ResponseEntity<>(UserMapper.customerToCustomerDto(cust), HttpStatus.OK);
		} catch (AuthenticationException | EShopException e) {
			return new ResponseEntity<>(HttpStatus.UNAUTHORIZED); // error 401
		}
	}
}
