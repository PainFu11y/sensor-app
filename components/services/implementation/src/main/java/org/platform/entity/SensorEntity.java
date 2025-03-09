package org.platform.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;
import org.platform.constants.DatabaseConstants;
import org.platform.dto.SensorDto;

import java.util.List;
import java.util.UUID;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = DatabaseConstants.SENSOR_TABLE, schema = DatabaseConstants.SCHEMA)
public class SensorEntity {
    @Id
    @GenericGenerator(name = "generator", strategy = "uuid2")
    @GeneratedValue(generator = "generator")
    private UUID id;

    @NotNull
    @Size(min = 1, max = 100)
    @Column(unique = true)
    private String name;

    @OneToMany(mappedBy = "sensor", cascade = CascadeType.ALL)
    private List<MeasurementEntity> measurements;

    public SensorDto toSensorDto(){
    SensorDto sensorDto = new SensorDto();
    sensorDto.setUserId(id);
    sensorDto.setName(name);
    return sensorDto;
    }
}
