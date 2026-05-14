package com.upc.pe.backend.servicecatalog.infrastructure.persistence.jpa.repositories;

import com.upc.pe.backend.servicecatalog.domain.model.aggregates.Service;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * ServiceRepository interface
 *
 * Repository responsible for
 * service persistence operations.
 */
@Repository
public interface ServiceRepository extends JpaRepository<Service, Long> {

    List<Service> findByCategoryId(Long categoryId);

    List<Service> findByTags_Id(Long tagId);

    List<Service> findByTitleContainingIgnoreCaseOrDescriptionContainingIgnoreCase(
            String title,
            String description
    );
}