package org.platform.controller;


import org.platform.constants.RoutConstants;
import org.platform.dto.SensorDto;
import org.platform.service.SensorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(RoutConstants.SENSORS)
public class SensorController {
    @Autowired
    private SensorService sensorService;

    @PostMapping("/registration")
    public @ResponseBody SensorDto registration(@RequestBody SensorDto sensorDto) {
        return sensorService.registerSensor(sensorDto);
    }

}
