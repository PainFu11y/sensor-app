package org.platform.springJpa;

import org.platform.dto.SensorDto;
import org.platform.entity.SensorEntity;
import org.platform.exceptions.sensorexceptons.SensorAlreadyExistsException;
import org.platform.exceptions.sensorexceptons.SensorApiException;
import org.platform.exceptions.sensorexceptons.SensorNotFoundException;
import org.platform.repository.SensorRepository;
import org.platform.service.SensorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class SensorSpringJpa implements SensorService {
    @Autowired
    private SensorRepository sensorRepository;

    @Override
    public SensorDto registerSensor(SensorDto sensorDto) {
        if (sensorDto.getName() == null || sensorDto.getName().isEmpty()) {
            throw new IllegalArgumentException("Sensor name cannot be null or empty");
        }
        SensorEntity sensorEntity;
        try {
            sensorEntity = sensorRepository.findByName(sensorDto.getName());
        }catch (Exception e){
            throw new SensorApiException("Problem while registering a sensor");
        }
        if(sensorEntity != null){
            throw new SensorAlreadyExistsException("Sensor already exists");
        }
        sensorEntity = new SensorEntity();
        sensorEntity.setName(sensorDto.getName());

        try {
            sensorRepository.save(sensorEntity);
        } catch (Exception e) {
            throw new SensorApiException("Problem while saving a sensor");
        }
        return sensorEntity.toSensorDto();
    }

    @Override
    public SensorDto getSensorById(UUID sensorId) {
        Optional<SensorEntity> sensorById = sensorRepository.findById(sensorId);
        if (sensorById.isEmpty()) {
            throw new SensorNotFoundException("Sensor not found");
        }
        return sensorById.get().toSensorDto();
    }
}
