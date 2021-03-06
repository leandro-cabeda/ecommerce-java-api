package com.ecommerce.security.jwt.exception;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.io.Serializable;
import java.util.function.Supplier;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class InvalidJwtAuthenticationException extends AuthenticationException{
	private static final long serialVersionUID = 435903908022307559L;

	public InvalidJwtAuthenticationException(String msg) {
		super(msg);
	}
	
	public static <T extends Serializable> Supplier<InvalidJwtAuthenticationException> invalidJwtAuthenticationException(final T invalidJwt) {
	    return () -> new InvalidJwtAuthenticationException("Error: "+ invalidJwt);
	}
}
