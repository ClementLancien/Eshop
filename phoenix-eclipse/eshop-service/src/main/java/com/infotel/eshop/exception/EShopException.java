package com.infotel.eshop.exception;

public class EShopException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public EShopException(String message, Throwable cause) {
		super(message, cause);
	}

	public EShopException(String message) {
		super(message);
	}

}
