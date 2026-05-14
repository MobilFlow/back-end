package com.upc.pe.backend.servicecatalog.domain.model.valueobjects;

import jakarta.persistence.Embeddable;

/**
 * Availability Value Object
 *
 * Represents the availability
 * information for a service.
 *
 * It can be used to indicate:
 * - Available schedules
 * - Working hours
 * - Service days
 */
@Embeddable
public class Availability {

    private String availableDays;
    private String availableHours;

    public Availability() {}

    public Availability(String availableDays, String availableHours) {

        if (availableDays == null || availableDays.isBlank()) {
            throw new IllegalArgumentException(
                    "availableDays cannot be blank"
            );
        }

        if (availableHours == null || availableHours.isBlank()) {
            throw new IllegalArgumentException(
                    "availableHours cannot be blank"
            );
        }

        this.availableDays = availableDays;
        this.availableHours = availableHours;
    }

    public String getAvailableDays() {
        return availableDays;
    }

    public String getAvailableHours() {
        return availableHours;
    }
}