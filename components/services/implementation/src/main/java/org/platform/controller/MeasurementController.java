package org.platform.controller;

import org.platform.constants.RoutConstants;
import org.platform.dto.MeasurementDto;
import org.platform.service.MeasurementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(RoutConstants.MEASUREMENTS)
public class MeasurementController {
    @Autowired
    private MeasurementService measurementService;

    @PostMapping("/add")
    @ResponseStatus(HttpStatus.CREATED)
    public @ResponseBody MeasurementDto addMeasurement(@RequestBody MeasurementDto measurementDto) {
        return measurementService.addMeasurement(measurementDto);
    }

    @GetMapping
    public List<MeasurementDto> getAllMeasurements() {
        return measurementService.getAllMeasurements();
    }

    @GetMapping("/rainyDaysCount")
    public long getRainyDaysCount() {
        return measurementService.getRainyDaysCount();
    }

}
