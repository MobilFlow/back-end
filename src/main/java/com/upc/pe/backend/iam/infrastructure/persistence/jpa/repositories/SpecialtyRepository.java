package com.upc.pe.backend.iam.infrastructure.persistence.jpa.repositories;

import com.upc.pe.backend.iam.domain.model.entities.Specialty;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SpecialtyRepository extends JpaRepository<Specialty, Long> {
    Optional<Specialty> findByName(String name);
    boolean existsByName(String name);
}
