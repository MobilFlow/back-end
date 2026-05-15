package com.upc.pe.backend.reputation.domain.model.aggregates;

import com.upc.pe.backend.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class MechanicReputation extends AuditableAbstractAggregateRoot<MechanicReputation> {

    @Column(nullable = false, unique = true)
    private Long mechanicId;

    @Column(nullable = false)
    private Double averageScore;

    @Column(nullable = false)
    private Integer ratingsCount;

    public MechanicReputation(Long mechanicId) {
        this.mechanicId = mechanicId;
        this.averageScore = 0.0;
        this.ratingsCount = 0;
    }

    public MechanicReputation() {
    }

    public void updateAverage(Double averageScore, Integer ratingsCount) {
        this.averageScore = averageScore;
        this.ratingsCount = ratingsCount;
    }
}
