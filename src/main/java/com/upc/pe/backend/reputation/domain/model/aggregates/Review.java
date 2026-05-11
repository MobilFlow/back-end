package com.upc.pe.backend.reputation.domain.model.aggregates;

import com.upc.pe.backend.reputation.domain.model.commands.CreateReviewCommand;
import com.upc.pe.backend.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Review extends AuditableAbstractAggregateRoot<Review> {

    @Column(nullable = false, length = 1000)
    private String content;

    @Column(nullable = false)
    private Long mechanicId;

    @Column(nullable = false)
    private Long driverId;

    @Column(nullable = false)
    private Long serviceId;

    @Column(nullable = false)
    private Boolean edited;

    public Review(String content, Long mechanicId, Long driverId, Long serviceId) {
        this.content = content;
        this.mechanicId = mechanicId;
        this.driverId = driverId;
        this.serviceId = serviceId;
        this.edited = false;
    }

    public Review(CreateReviewCommand command) {
        this.content = command.content();
        this.mechanicId = command.mechanicId();
        this.driverId = command.driverId();
        this.serviceId = command.serviceId();
        this.edited = false;
    }

    public Review() {
    }

    public void updateContent(String content) {
        this.content = content;
        this.edited = true;
    }
}
