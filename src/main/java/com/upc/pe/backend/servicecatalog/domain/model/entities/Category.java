package com.upc.pe.backend.servicecatalog.domain.model.entities;

import com.upc.pe.backend.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

/**
 * Category entity for ServiceCatalog.
 *
 * This entity represents a service category
 * used to classify services published by mechanics.
 *
 * Categories allow filtering and grouping
 * services inside the platform.
 *
 * Examples:
 * - Engine Repair
 * - Oil Change
 * - Electrical Systems
 *
 * @see AuditableAbstractAggregateRoot
 */
@Entity
@Getter
@Setter
public class Category extends AuditableAbstractAggregateRoot<Category> {

    @NotBlank
    @Column(nullable = false, unique = true)
    private String name;

    public Category() {}

    public Category(String name) {
        this.name = name;
    }
}