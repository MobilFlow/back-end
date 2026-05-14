package com.upc.pe.backend.servicecatalog.domain.model.valueobjects;

import jakarta.persistence.Embeddable;

/**
 * ServiceDetails Value Object
 *
 * Represents additional details
 * associated with a service.
 *
 * It may contain:
 * - Technical specifications
 * - Service conditions
 * - Additional descriptions
 */
@Embeddable
public class ServiceDetails {

    private String details;

    public ServiceDetails() {}

    public ServiceDetails(String details) {

        if (details == null || details.isBlank()) {
            throw new IllegalArgumentException("details cannot be blank");
        }

        this.details = details;
    }

    public String getDetails() {
        return details;
    }
}