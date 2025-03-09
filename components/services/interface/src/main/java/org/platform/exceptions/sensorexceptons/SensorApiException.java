package org.platform.exceptions.sensorexceptons;

import org.platform.exceptions.ApiException;

public class SensorApiException extends ApiException {
    public SensorApiException(String errorMessage) {
        super(errorMessage);
    }
}
