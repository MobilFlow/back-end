package com.upc.pe.backend.servicecatalog.domain.model.entities;

import com.upc.pe.backend.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

/**
 * Tag entity for ServiceCatalog.
 *
 * This entity represents tags associated
 * with services inside the platform.
 *
 * Tags are used to improve:
 * - Search
 * - Filtering
 * - Recommendations
 *
 * Examples:
 * - Fast Service
 * - Home Service
 * - Certified
 *
 * @see AuditableAbstractAggregateRoot
 */
@Entity
@Getter
@Setter
public class Tag extends AuditableAbstractAggregateRoot<Tag> {

    @NotBlank
    @Column(nullable = false, unique = true)
    private String name;

    public Tag() {}

    public Tag(String name) {
        this.name = name;
    }
}