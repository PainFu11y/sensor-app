package org.platform.service;

import org.platform.dto.MeasurementDto;

import java.util.List;

public interface MeasurementService {
    MeasurementDto addMeasurement(MeasurementDto measurementDto);
    List<MeasurementDto> getAllMeasurements();
    long getRainyDaysCount();
}
