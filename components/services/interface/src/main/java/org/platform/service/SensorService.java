package org.platform.service;

import org.platform.dto.SensorDto;

import java.util.UUID;

public interface SensorService {
    SensorDto registerSensor(SensorDto sensor);
    SensorDto getSensorById(UUID sensorId);

}
