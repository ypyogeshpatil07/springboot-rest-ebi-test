package com.ebi.springboot.exception;

import java.util.Map;

/**
 * Custom exception to mark any JSR-303 validation errors.
 *
 * @author Yogesh Patil
 */
public class InvalidRequestException extends Exception {
    
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final Map<String, String> errors;

    public Map<String, String> getErrors() {
		return errors;
	}
    




	/**
     * constructor
     *
     * @param errors validation errors
     */
    public InvalidRequestException(Map<String, String> errors) {
        this.errors = errors;
    }
}
