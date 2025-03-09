package org.platform.dto;


import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SensorDto {
    @Schema(description = "unique identifier of the sensor", hidden = true)
    private UUID userId;

    @NotEmpty(message = "Sensor name must not be empty")
    private String name;
}
