package org.platform.exceptions.userexceptions;

public class PasswordDontMatchException extends RuntimeException{
    public PasswordDontMatchException(String errorMessage) {
        super(errorMessage);
    }
}
