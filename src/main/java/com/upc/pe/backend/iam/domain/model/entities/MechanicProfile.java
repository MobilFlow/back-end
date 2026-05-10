package com.upc.pe.backend.iam.domain.model.entities;

import com.upc.pe.backend.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;
import jakarta.persistence.*;
import org.springframework.context.annotation.Profile;

import java.util.HashSet;
import java.util.Set;

public class MechanicProfile extends AuditableAbstractAggregateRoot<MechanicProfile>{

        @Column(nullable = false, unique = true)
        private Long userId;

        @Column(length = 500)
        private String description;

        @Column
        private String workshopName;

        @Column
        private String workshopAddress;

        @ManyToMany(fetch = FetchType.EAGER)
        @JoinTable(
                name = "mechanic_profile_specialties",
                joinColumns = @JoinColumn(name = "mechanic_profile_id"),
                inverseJoinColumns = @JoinColumn(name = "specialty_id")
        )
        private Set<Specialty> specialties = new HashSet<>();

        public MechanicProfile(Long userId) {
            this.userId = userId;
        }

        public MechanicProfile(Long userId, String description, String workshopName,
                               String workshopAddress) {
            this.userId          = userId;
            this.description             = description;
            this.workshopName    = workshopName;
            this.workshopAddress = workshopAddress;
        }

        public void addSpecialty(Specialty specialty) {
            this.specialties.add(specialty);
        }

        public void removeSpecialty(Specialty specialty) {
            this.specialties.remove(specialty);
        }
    }