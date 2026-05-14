package com.upc.pe.backend.servicecatalog.domain.model.aggregates;

import com.upc.pe.backend.servicecatalog.domain.model.entities.Category;
import com.upc.pe.backend.servicecatalog.domain.model.entities.ServiceImage;
import com.upc.pe.backend.servicecatalog.domain.model.entities.Tag;
import com.upc.pe.backend.servicecatalog.domain.model.valueobjects.PriceRange;
import com.upc.pe.backend.servicecatalog.domain.model.valueobjects.ServiceStatus;
import com.upc.pe.backend.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.util.ArrayList;
import java.util.List;

/**
 * Service Aggregate Root for ServiceCatalog.
 *
 * This entity represents the core service
 * published by mechanics within the platform.
 *
 * It is responsible for managing:
 * - Service information
 * - Service category
 * - Tags
 * - Images
 * - Service state
 *
 * This aggregate guarantees consistency
 * for all service-related operations inside
 * the ServiceCatalog bounded context.
 *
 * @see AuditableAbstractAggregateRoot
 */
@Entity
@Getter
@Setter
@Table(name = "services")
@EntityListeners(AuditingEntityListener.class)
public class Service extends AuditableAbstractAggregateRoot<Service> {

    @Column(nullable = false)
    private Long mechanicProfileId;

    @NotBlank
    @Column(nullable = false)
    private String title;

    @NotBlank
    @Column(nullable = false, length = 1000)
    private String description;

    @Embedded
    private PriceRange priceRange;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ServiceStatus status;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "category_id")
    private Category category;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "service_tags",
            joinColumns = @JoinColumn(name = "service_id"),
            inverseJoinColumns = @JoinColumn(name = "tag_id")
    )
    private List<Tag> tags = new ArrayList<>();

    @OneToMany(
            mappedBy = "service",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<ServiceImage> images = new ArrayList<>();

    public Service() {}

    public Service(
            Long mechanicProfileId,
            String title,
            String description,
            PriceRange priceRange,
            ServiceStatus status,
            Category category
    ) {
        this.mechanicProfileId = mechanicProfileId;
        this.title = title;
        this.description = description;
        this.priceRange = priceRange;
        this.status = status;
        this.category = category;
    }

    /**
     * Adds a tag to the service.
     *
     * @param tag the tag to add
     */
    public void addTag(Tag tag) {
        if (!tags.contains(tag)) {
            tags.add(tag);
        }
    }

    /**
     * Removes a tag from the service.
     *
     * @param tag the tag to remove
     */
    public void removeTag(Tag tag) {
        tags.remove(tag);
    }

    /**
     * Adds an image to the service.
     *
     * @param image the image to add
     */
    public void addImage(ServiceImage image) {
        images.add(image);
        image.setService(this);
    }

    /**
     * Deactivates the service.
     */
    public void deactivate() {
        this.status = ServiceStatus.INACTIVE;
    }
}