package com.ecommerce.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.io.Serializable;
import java.util.function.Supplier;


@ResponseStatus(HttpStatus.NOT_FOUND)
public class NotFound extends RuntimeException{
	private static final long serialVersionUID = 1071464351981156149L;

	public NotFound(String msg) {
		super(msg);
	}
	
	public static <T extends Serializable> Supplier<NotFound> notFound(final T notFound) {
	    return () -> new NotFound("Error: "+ notFound);
	}
}
