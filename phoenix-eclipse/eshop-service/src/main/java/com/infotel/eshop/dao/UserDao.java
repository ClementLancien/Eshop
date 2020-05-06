package com.infotel.eshop.dao;

import com.infotel.eshop.exception.EShopException;
import com.infotel.eshop.model.User;

public interface UserDao {

	User findByUsername(String username) throws EShopException;

	void create(User user) throws EShopException;

}