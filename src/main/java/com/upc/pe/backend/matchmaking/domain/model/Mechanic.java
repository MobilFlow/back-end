package com.upc.pe.backend.matchmaking.domain.model;

import com.upc.pe.backend.geolocalization.domain.model.aggregates.MechanicLocation;
import com.upc.pe.backend.iam.domain.model.aggregates.User;
import com.upc.pe.backend.reputation.domain.model.aggregates.MechanicReputation;
import com.upc.pe.backend.servicecatalog.domain.model.entities.Tag;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "matchmaking_mechanics")
@Getter
@Setter
@NoArgsConstructor
public class Mechanic {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id", nullable = false, unique = true)
    private User user;

    @Column
    private String description;

    @Column(name = "workshop_name")
    private String workshopName;

    @Column(name = "workshop_address")
    private String workshopAddress;

    // ← ELIMINADAS las relaciones mappedBy inválidas
    // location y reputation se obtienen desde sus propios repositorios por mechanicId

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "mechanic_profile_specialties",
            joinColumns = @JoinColumn(name = "mechanic_profile_id"),
            inverseJoinColumns = @JoinColumn(name = "specialty_id")
    )
    private List<Tag> tags;
}