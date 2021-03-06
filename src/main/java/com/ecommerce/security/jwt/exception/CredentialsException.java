package com.ecommerce.security.jwt.exception;

import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.io.Serializable;
import java.util.function.Supplier;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class CredentialsException extends BadCredentialsException {
	private static final long serialVersionUID = 7367772134581150138L;

	public CredentialsException(String msg) {
		super(msg);
	}

	public static <T extends Serializable> Supplier<CredentialsException> credentialsException(
			final T credentialsException) {
		return () -> new CredentialsException("Error: " + credentialsException);
	}
}
