package com.ebi.springboot.exception;

import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Custom Exception
 * @author Yogesh Patil
 *
 */
@ResponseStatus
public class ResourceNotFoundException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;

	public ResourceNotFoundException(String message) {
		super(message);
	}
	
	public ResourceNotFoundException(String message, Throwable throwable) {
		super(message, throwable);
	}
}
