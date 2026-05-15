package com.upc.pe.backend.reputation.domain.model.aggregates;

import com.upc.pe.backend.reputation.domain.model.commands.CreateRatingCommand;
import com.upc.pe.backend.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Rating extends AuditableAbstractAggregateRoot<Rating> {

    @Column(nullable = false)
    private Integer score;

    @Column(nullable = false)
    private Long mechanicId;

    @Column(nullable = false)
    private Long driverId;

    @Column(nullable = false)
    private Long serviceId;

    @Column(nullable = false)
    private Boolean edited;

    public Rating(Integer score, Long mechanicId, Long driverId, Long serviceId) {
        this.score = score;
        this.mechanicId = mechanicId;
        this.driverId = driverId;
        this.serviceId = serviceId;
        this.edited = false;
    }

    public Rating(CreateRatingCommand command) {
        this.score = command.score();
        this.mechanicId = command.mechanicId();
        this.driverId = command.driverId();
        this.serviceId = command.serviceId();
        this.edited = false;
    }

    public Rating() {
    }

    public void updateScore(Integer score) {
        this.score = score;
        this.edited = true;
    }
}
