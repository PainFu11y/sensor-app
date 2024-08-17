package org.platform.exceptions.accountexceptions;

import org.platform.exceptions.ResourceNotFoundException;

public class AccountNotFoundException extends ResourceNotFoundException {
    public AccountNotFoundException(String errorMessage) {
        super(errorMessage);
    }
}
