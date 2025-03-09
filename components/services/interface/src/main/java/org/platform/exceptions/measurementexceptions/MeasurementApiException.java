package org.platform.exceptions.measurementexceptions;

import org.platform.exceptions.ApiException;

public class MeasurementApiException extends ApiException {
    public MeasurementApiException(String errorMessage) {
        super(errorMessage);
    }
}
