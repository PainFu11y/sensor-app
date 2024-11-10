package org.platform;

import org.platform.exceptions.ForbiddenException;

public class SecurityException extends ForbiddenException {
    public SecurityException() {
        super("Insufficient privileges");
    }
}
