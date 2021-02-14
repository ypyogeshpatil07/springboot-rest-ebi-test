package com.ebi.springboot.exception;

import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

/**
 * Custom exception handler to handle InvalidRequestException.
 *
 * @author Yogesh Patil
 * @see InvalidRequestException
 */
@ControllerAdvice
@RestController
public class InvalidRequestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    /**
     * @param ex      exception instance
     * @param request HTTP request
     *
     * @return response with 400 response together with violation constraints.
     */
    @ExceptionHandler(InvalidRequestException.class)
    public final ResponseEntity<Map<String, String>> handleInvalidRequestException(InvalidRequestException ex,
                                                                                   WebRequest request) {
        return new ResponseEntity<>(ex.getErrors() ,HttpStatus.BAD_REQUEST);
    }
}
