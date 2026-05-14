package com.upc.pe.backend.servicecatalog.domain.model.entities;

import com.upc.pe.backend.servicecatalog.domain.model.aggregates.Service;
import com.upc.pe.backend.servicecatalog.domain.model.valueobjects.MediaUrl;
import com.upc.pe.backend.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

/**
 * ServiceImage entity for ServiceCatalog.
 *
 * This entity represents images associated
 * with services published by mechanics.
 *
 * Images are used to visually describe
 * services inside the platform.
 *
 * @see AuditableAbstractAggregateRoot
 */
@Entity
@Getter
@Setter
public class ServiceImage extends AuditableAbstractAggregateRoot<ServiceImage> {

    @Embedded
    private MediaUrl imageUrl;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "service_id")
    private Service service;

    public ServiceImage() {}

    public ServiceImage(MediaUrl imageUrl) {
        this.imageUrl = imageUrl;
    }
}