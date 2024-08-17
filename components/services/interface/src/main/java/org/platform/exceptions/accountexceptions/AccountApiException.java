package org.platform.exceptions.accountexceptions;

import org.platform.exceptions.ApiException;

public class AccountApiException extends ApiException {
    public AccountApiException(String errorMessage) {
        super(errorMessage);
    }
}
