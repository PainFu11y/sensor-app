package org.platform.exceptions.sensorexceptons;

import org.platform.exceptions.BadRequestException;

public class SensorBadRequestException extends BadRequestException {
    public SensorBadRequestException(String errorMessage) {
        super(errorMessage);
    }
}
