package com.upc.pe.backend.iam.infrastructure.persistence.jpa.repositories;

import com.upc.pe.backend.iam.domain.model.entities.Car;
import com.upc.pe.backend.iam.domain.model.entities.DriverProfile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DriverProfileRepository extends JpaRepository<DriverProfile, Long> {
    Optional<DriverProfile> findByUserId(Long userId);
    boolean existsByUserId(Long userId);
}

