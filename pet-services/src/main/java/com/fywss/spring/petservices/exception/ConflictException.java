package com.fywss.spring.petservices.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class ConflictException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public ConflictException(String exception) {
		super(exception);
	}
}
