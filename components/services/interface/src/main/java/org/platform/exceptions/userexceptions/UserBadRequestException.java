package org.platform.exceptions.userexceptions;

import org.platform.exceptions.BadRequestException;

public class UserBadRequestException extends BadRequestException {

    public UserBadRequestException(String errorMessage) {
        super(errorMessage);
    }
}
