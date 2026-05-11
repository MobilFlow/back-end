package com.upc.pe.backend.servicecatalog.infrastructure.persistence.jpa.repositories;

import com.upc.pe.backend.servicecatalog.domain.model.entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * CategoryRepository interface
 *
 * Repository responsible for
 * category persistence operations.
 */
@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {

    boolean existsByName(String name);
}