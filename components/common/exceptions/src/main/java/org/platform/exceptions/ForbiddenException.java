package org.platform.exceptions;

public class ForbiddenException extends RuntimeException{
    public ForbiddenException(String errorMessage) {
        super(errorMessage);
    }
}
