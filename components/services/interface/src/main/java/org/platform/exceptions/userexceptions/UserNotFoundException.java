package org.platform.exceptions.userexceptions;

import org.platform.exceptions.ResourceNotFoundException;

public class UserNotFoundException extends ResourceNotFoundException {

    public UserNotFoundException(String errorMessage) {
        super(errorMessage);
    }
}
