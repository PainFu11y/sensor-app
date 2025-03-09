package org.platform.exceptions.sensorexceptons;

import org.platform.exceptions.ResourceAlreadyExistsException;

public class SensorAlreadyExistsException extends ResourceAlreadyExistsException {
    public SensorAlreadyExistsException(String errorMessage) {
        super(errorMessage);
    }
}
