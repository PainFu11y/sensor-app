package org.platform.exceptions.userexceptions;

import org.platform.exceptions.ResourceAlreadyExistsException;

public class UserAlreadyExistException extends ResourceAlreadyExistsException {
    public UserAlreadyExistException(String errorMessage) {
        super(errorMessage);
    }
}
