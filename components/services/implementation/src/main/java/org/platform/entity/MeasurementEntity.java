package org.platform.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;
import org.platform.constants.DatabaseConstants;
import org.platform.dto.MeasurementDto;

import java.util.UUID;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = DatabaseConstants.MEASUREMENT_TABLE, schema = DatabaseConstants.SCHEMA)
public class MeasurementEntity {
    @Id
    @GenericGenerator(name = "generator", strategy = "uuid2")
    @GeneratedValue(generator = "generator")
    private UUID id;

    @NotNull
    private Double value;

    @NotNull
    private Boolean raining;

    @ManyToOne
    @JoinColumn(name = "sensor_id")
    private SensorEntity sensor;

    public MeasurementDto toMeasurementDto() {
        MeasurementDto measurementDto = new MeasurementDto();
        measurementDto.setMeasurementId(id);
        measurementDto.setValue(value);
        measurementDto.setRaining(raining);
        return measurementDto;
    }
}
