package org.platform.repository;

import org.platform.entity.MeasurementEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface MeasurementRepository extends JpaRepository<MeasurementEntity, UUID> {
    long countByRaining(boolean raining);
}
