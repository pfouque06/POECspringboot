package com.restapi.many2many.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public ResourceNotFoundException() {}

    public ResourceNotFoundException(String entity, Long id) {
        super(entity + " id " + id + " not found");
    }
    
}
