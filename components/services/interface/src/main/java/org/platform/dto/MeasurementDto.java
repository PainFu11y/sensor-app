package org.platform.dto;

import io.swagger.v3.oas.annotations.Hidden;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class MeasurementDto {
    @Hidden
    UUID measurementId;
    @NotNull(message = "Temperature value must not be null")
    private Double value;

    @NotNull(message = "Raining status must not be null")
    private Boolean raining;

    @NotNull(message = "Sensor must not be null")
    private SensorDto sensor;
}
