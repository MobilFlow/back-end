package com.upc.pe.backend.servicemanagement.domain.model.aggregates;

import com.upc.pe.backend.servicemanagement.domain.model.commands.CreateDiagnosisCommand;
import com.upc.pe.backend.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Diagnosis extends AuditableAbstractAggregateRoot<Diagnosis> {

    @Column(nullable = false, unique = true)
    private Long serviceRequestId;

    @Column(nullable = false, length = 2000)
    private String summary;

    @Column(nullable = false)
    private String recommendedSpecialty;

    public Diagnosis() {}

    public Diagnosis(Long serviceRequestId, String summary, String recommendedSpecialty) {
        this.serviceRequestId = serviceRequestId;
        this.summary = summary;
        this.recommendedSpecialty = recommendedSpecialty;
    }

    public Diagnosis(CreateDiagnosisCommand command) {
        this.serviceRequestId = command.serviceRequestId();
        this.summary = command.summary();
        this.recommendedSpecialty = command.recommendedSpecialty();
    }
}
