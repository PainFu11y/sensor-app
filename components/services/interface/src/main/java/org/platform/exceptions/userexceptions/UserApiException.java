package org.platform.exceptions.userexceptions;

import org.platform.exceptions.ApiException;

public class UserApiException extends ApiException {
    public UserApiException(String errorMessage) {
        super(errorMessage);
    }
}
