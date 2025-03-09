package org.platform.exceptions.measurementexceptions;

import org.platform.exceptions.ResourceNotFoundException;

public class MeasurementNotFoundException extends ResourceNotFoundException {
    public MeasurementNotFoundException(String message) {
        super(message);
    }
}
