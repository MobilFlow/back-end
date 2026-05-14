package com.upc.pe.backend.servicecatalog.domain.model.valueobjects;

import jakarta.persistence.Embeddable;

import java.math.BigDecimal;

/**
 * PriceRange Value Object
 *
 * This value object represents
 * the minimum and maximum price
 * range for a service.
 *
 * It is used to define estimated
 * service pricing inside the platform.
 */
@Embeddable
public class PriceRange {

    private BigDecimal minimumPrice;
    private BigDecimal maximumPrice;

    public PriceRange() {}

    public PriceRange(Double minimumPrice, Double maximumPrice) {

        if (minimumPrice == null || maximumPrice == null) {
            throw new IllegalArgumentException("Prices cannot be null");
        }

        if (minimumPrice < 0 || maximumPrice < 0) {
            throw new IllegalArgumentException("Prices cannot be negative");
        }

        if (minimumPrice > maximumPrice) {
            throw new IllegalArgumentException(
                    "Minimum price cannot be greater than maximum price"
            );
        }

        this.minimumPrice = BigDecimal.valueOf(minimumPrice);
        this.maximumPrice = BigDecimal.valueOf(maximumPrice);
    }

    public BigDecimal getMinimumPrice() {
        return minimumPrice;
    }

    public BigDecimal getMaximumPrice() {
        return maximumPrice;
    }
}