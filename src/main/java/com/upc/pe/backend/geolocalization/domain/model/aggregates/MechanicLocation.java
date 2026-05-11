package com.upc.pe.backend.geolocalization.domain.model.aggregates;

import com.upc.pe.backend.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class MechanicLocation extends AuditableAbstractAggregateRoot<MechanicLocation> {

    @Column(nullable = false, unique = true)
    private Long mechanicId;

    @Column(nullable = false)
    private Double latitude;

    @Column(nullable = false)
    private Double longitude;

    @Column
    private String addressText;

    /**
     * Creates a new MechanicLocation for the given mechanicId.
     *
     * @param mechanicId  ID of the Mechanic in the IAM BC
     * @param latitude    current latitude
     * @param longitude   current longitude
     * @param addressText optional human-readable address
     */
    public MechanicLocation(Long mechanicId, Double latitude, Double longitude, String addressText) {
        this.mechanicId  = mechanicId;
        this.latitude    = latitude;
        this.longitude   = longitude;
        this.addressText = addressText;
    }

    public MechanicLocation() {}

    /**
     * Updates the mechanic's registered geolocation.
     * Called every time the mechanic registers a new position.
     *
     * @param latitude    new latitude
     * @param longitude   new longitude
     * @param addressText new human-readable address
     */
    public void updateLocation(Double latitude, Double longitude, String addressText) {
        this.latitude    = latitude;
        this.longitude   = longitude;
        this.addressText = addressText;
    }

}
