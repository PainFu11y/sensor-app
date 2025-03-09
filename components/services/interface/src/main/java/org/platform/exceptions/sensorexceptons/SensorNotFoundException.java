package org.platform.exceptions.sensorexceptons;

import org.platform.exceptions.ResourceNotFoundException;

public class SensorNotFoundException extends ResourceNotFoundException {
    public SensorNotFoundException(String errorMessage) {
        super(errorMessage);
    }
}
