package com.upc.pe.backend.geolocalization.infrastructure.persistance.jpa.repositories;

import com.upc.pe.backend.geolocalization.domain.model.aggregates.MechanicLocation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MechanicLocationRepository extends JpaRepository<MechanicLocation, Long> {

    Optional<MechanicLocation> findByMechanicId(Long mechanicId);

    boolean existsByMechanicId(Long mechanicId);
}