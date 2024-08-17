package org.platform.exceptions.accountexceptions;

import org.platform.exceptions.ResourceAlreadyExistsException;

public class AccountAlreadyExistException extends ResourceAlreadyExistsException {
    public AccountAlreadyExistException(String errorMessage) {
        super(errorMessage);
    }
}
