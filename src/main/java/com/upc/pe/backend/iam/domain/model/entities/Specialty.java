package com.upc.pe.backend.iam.domain.model.entities;

import com.upc.pe.backend.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Specialty extends AuditableAbstractAggregateRoot<Specialty> {

    @Column(nullable = false, unique = true, length = 60)
    private String name;

    public Specialty(String name) {
        this.name = name;
    }

    public Specialty() {}
}