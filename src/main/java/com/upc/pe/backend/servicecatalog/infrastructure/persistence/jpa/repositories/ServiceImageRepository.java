package com.upc.pe.backend.servicecatalog.infrastructure.persistence.jpa.repositories;

import com.upc.pe.backend.servicecatalog.domain.model.entities.ServiceImage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * ServiceImageRepository interface
 *
 * Repository responsible for
 * service image persistence operations.
 */
@Repository
public interface ServiceImageRepository
        extends JpaRepository<ServiceImage, Long> {
}