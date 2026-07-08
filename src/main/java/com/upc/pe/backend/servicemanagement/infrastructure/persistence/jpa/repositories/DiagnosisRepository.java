package com.upc.pe.backend.servicemanagement.infrastructure.persistence.jpa.repositories;

import com.upc.pe.backend.servicemanagement.domain.model.aggregates.Diagnosis;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DiagnosisRepository extends JpaRepository<Diagnosis, Long> {
    Optional<Diagnosis> findByServiceRequestId(Long serviceRequestId);
    boolean existsByServiceRequestId(Long serviceRequestId);
}
