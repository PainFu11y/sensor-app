package org.platform.exceptions;

public abstract class ApiException extends RuntimeException{
    public ApiException(String errorMessage){
        super(errorMessage);
    }
}
