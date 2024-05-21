package com.traini8.exceptions;

import lombok.Builder;

@Builder
public class ResourceNotFoundException extends RuntimeException{

    
	private static final long serialVersionUID = -1386845045586030516L;

	public ResourceNotFoundException() {
        super("Resource Not Found!");

    }

    public ResourceNotFoundException(String message) {
        super(message);
    }

}
