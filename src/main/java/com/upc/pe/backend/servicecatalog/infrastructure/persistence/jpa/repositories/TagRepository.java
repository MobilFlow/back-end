package com.upc.pe.backend.servicecatalog.infrastructure.persistence.jpa.repositories;

import com.upc.pe.backend.servicecatalog.domain.model.entities.Tag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * TagRepository interface
 *
 * Repository responsible for
 * tag persistence operations.
 */
@Repository
public interface TagRepository extends JpaRepository<Tag, Long> {

    boolean existsByName(String name);
}