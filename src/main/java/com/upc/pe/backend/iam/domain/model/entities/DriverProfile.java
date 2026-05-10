package com.upc.pe.backend.iam.domain.model.entities;

import com.upc.pe.backend.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
public class DriverProfile extends AuditableAbstractAggregateRoot<DriverProfile> {

    @Column(nullable = false, unique = true)
    private Long userId;

    @OneToMany(mappedBy = "driverProfile", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Car> cars = new ArrayList<>();

    public DriverProfile(Long userId) {
        this.userId = userId;
    }

    public DriverProfile() {
    }


    public void addCar(Car car) {
        car.setDriverProfile(this);
        this.cars.add(car);
    }

    public void removeCar(Car car) {
        this.cars.remove(car);
        car.setDriverProfile(null);
    }
}
