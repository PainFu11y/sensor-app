package org.platform.exceptions.userexceptions;

public class InvalidOldPasswordException extends RuntimeException{
    public InvalidOldPasswordException(String errorMessage) {
        super(errorMessage);
    }
}
