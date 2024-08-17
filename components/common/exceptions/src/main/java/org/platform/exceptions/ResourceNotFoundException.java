package org.platform.exceptions;

import org.springframework.http.HttpStatus;

public class ResourceNotFoundException extends RuntimeException{
    public ResourceNotFoundException(String errorMessage){
        super(errorMessage);

    }
}
