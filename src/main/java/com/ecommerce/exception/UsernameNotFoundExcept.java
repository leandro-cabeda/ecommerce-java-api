package com.ecommerce.exception;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.io.Serializable;
import java.util.function.Supplier;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class UsernameNotFoundExcept extends UsernameNotFoundException {
	private static final long serialVersionUID = -2678034557236318133L;

	public UsernameNotFoundExcept(String msg) {
		super(msg);
	}

	public static <T extends Serializable> Supplier<UsernameNotFoundExcept> usernameNotFound(final T usernameNotFound) {
		return () -> new UsernameNotFoundExcept("Error: " + usernameNotFound);
	}
}
