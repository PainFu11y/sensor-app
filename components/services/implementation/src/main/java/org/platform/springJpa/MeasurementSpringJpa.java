package org.platform.springJpa;

import org.platform.dto.MeasurementDto;
import org.platform.entity.MeasurementEntity;
import org.platform.entity.SensorEntity;
import org.platform.exceptions.measurementexceptions.MeasurementApiException;
import org.platform.exceptions.sensorexceptons.SensorApiException;
import org.platform.exceptions.sensorexceptons.SensorNotFoundException;
import org.platform.repository.MeasurementRepository;
import org.platform.repository.SensorRepository;
import org.platform.service.MeasurementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MeasurementSpringJpa implements MeasurementService {
    @Autowired
    private MeasurementRepository measurementRepository;
    @Autowired
    private SensorRepository sensorRepository;

    @Override
    public MeasurementDto addMeasurement(MeasurementDto measurementDto) {
        if (measurementDto.getSensor() == null || measurementDto.getSensor().getName() == null) {
            throw new IllegalArgumentException("Measurement or sensor name cannot be null");
        }
        SensorEntity sensorEntity;
        try{
            sensorEntity = sensorRepository.findByName(measurementDto.getSensor().getName());
        } catch (Exception e){
            throw new SensorApiException("Problem finding sensor");
        }
        if (sensorEntity == null) {
            throw new SensorNotFoundException("Sensor not found: " + measurementDto.getSensor().getName());
        }
        MeasurementEntity measurementEntity = new MeasurementEntity();
        measurementEntity.setValue(measurementDto.getValue());
        measurementEntity.setRaining(measurementDto.getRaining());
        measurementEntity.setSensor(sensorEntity);

        try {
            measurementRepository.save(measurementEntity);
        } catch (Exception e) {
            throw new MeasurementApiException("Problem while saving a measurement");
        }
        return measurementEntity.toMeasurementDto();
    }

    @Override
    public List<MeasurementDto> getAllMeasurements() {
        List<MeasurementEntity> measurementEntities;
        try {
            measurementEntities = measurementRepository.findAll();
        } catch (Exception e) {
            throw new MeasurementApiException("Problem while getting measurements");
        }
        return measurementEntities
                .stream()
                .map(MeasurementEntity::toMeasurementDto)
                .toList();
    }

    @Override
    public long getRainyDaysCount() {
        long rainyDaysCount = 0;
        try {
           rainyDaysCount = measurementRepository.countByRaining(true);
        }catch (Exception e){
            throw new MeasurementApiException("Problem while counting rain days");
        }
        return rainyDaysCount;
    }
}
