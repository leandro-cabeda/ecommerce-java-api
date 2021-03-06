package com.ecommerce.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.io.Serializable;
import java.util.function.Supplier;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class RegisterExists extends RuntimeException {
	private static final long serialVersionUID = -6483711994542861021L;

	public RegisterExists(String msg) {
		super(msg);
	}
	
	public static <T extends Serializable> Supplier<RegisterExists> registerExists(final T register) {
	    return () -> new RegisterExists("Error: "+ register);
	}
}
