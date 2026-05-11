package com.upc.pe.backend.iam.infrastructure.persistence.jpa.repositories;

import com.upc.pe.backend.iam.domain.model.entities.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CarRepository extends JpaRepository<Car, Long> {
    List<Car> findByDriverProfileId(Long driverProfileId);
    List<Car> findByOwnerId(Long ownerId);
    boolean existsByPlate(String plate);
}
