package com.upc.pe.backend.iam.domain.model.entities;

import com.upc.pe.backend.iam.domain.model.valueobjects.FuelType;
import com.upc.pe.backend.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;
import jakarta.persistence.*;
import jakarta.persistence.Entity;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Car extends AuditableAbstractAggregateRoot<Car> {

    @Column(nullable = false)
    private Long ownerId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "driver_profile_id", nullable = false)
    private DriverProfile driverProfile;

    @NotBlank
    @Column(nullable = false)
    private String brand;

    @NotBlank
    @Column(nullable = false)
    private String model;

    @NotNull
    @Column(nullable = false)
    private Integer year;

    @NotBlank
    @Column(nullable = false, unique = true)
    private String plate;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private FuelType fuelType;

    public Car() {}
    public Car(Long ownerId, String brand, String model,
               Integer year, String plate, FuelType fuelType) {
        this.ownerId  = ownerId;
        this.brand    = brand;
        this.model    = model;
        this.year     = year;
        this.plate    = plate;
        this.fuelType = fuelType;
    }
}
