package org.example.quantityservice.repository;

import org.example.quantityservice.entity.QuantityMeasurementEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuantityMeasurementRepository
        extends JpaRepository<
        QuantityMeasurementEntity,
        Integer> {

}
