package com.upc.pe.backend.iam.application.internal.commandservices;

import com.upc.pe.backend.iam.domain.model.commands.*;
import com.upc.pe.backend.iam.domain.model.entities.Car;
import com.upc.pe.backend.iam.domain.model.entities.DriverProfile;
import com.upc.pe.backend.iam.domain.services.DriverProfileCommandService;
import com.upc.pe.backend.iam.infrastructure.persistence.jpa.repositories.CarRepository;
import com.upc.pe.backend.iam.infrastructure.persistence.jpa.repositories.DriverProfileRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class DriverProfileCommandServiceImpl implements DriverProfileCommandService {

    private final DriverProfileRepository driverProfileRepository;
    private final CarRepository carRepository;

    public DriverProfileCommandServiceImpl(DriverProfileRepository driverProfileRepository,
                                           CarRepository carRepository) {
        this.driverProfileRepository = driverProfileRepository;
        this.carRepository           = carRepository;
    }

    /** Creates a DriverProfile for an existing User. */
    @Override
    @Transactional
    public Optional<DriverProfile>  handle(CreateDriverProfileCommand command) {
        if (driverProfileRepository.existsByUserId(command.userId()))
            throw new IllegalStateException(
                    String.format("Driver profile already exists for user %d", command.userId()));

        var profile = new DriverProfile(
                command.userId()
        );
        return Optional.of(driverProfileRepository.save(profile));
    }

    /** Updates license info on an existing DriverProfile. */
    @Override
    public Optional<DriverProfile> handle(UpdateDriverProfileCommand command) {
        var profile = driverProfileRepository.findById(command.driverProfileId())
                .orElseThrow(() -> new IllegalArgumentException(
                        String.format("DriverProfile %d not found", command.driverProfileId())));

        return Optional.of(driverProfileRepository.save(profile));
    }

    /** Registers a new Car under the given DriverProfile. */
    @Override
@Transactional
public Optional<Car> handle(RegisterCarCommand command) {
    var profile = driverProfileRepository.findById(command.driverProfileId())
            .orElseThrow(() -> new IllegalArgumentException(
                    String.format("DriverProfile %d not found", command.driverProfileId())));

    if (carRepository.existsByPlate(command.plate()))
        throw new IllegalArgumentException(
                String.format("Plate %s is already registered", command.plate()));

    var car = new Car(
            command.driverProfileId(),
            command.brand(),
            command.model(),
            command.year(),
            command.plate(),
            command.fuelType()
    );

    car.setDriverProfile(profile);

    Car savedCar = carRepository.save(car);

    return Optional.of(savedCar);
}

    /** Updates an existing Car's details. */
    @Override
    public Optional<Car> handle(UpdateCarCommand command) {
        var car = carRepository.findById(command.carId())
                .orElseThrow(() -> new IllegalArgumentException(
                        String.format("Car %d not found", command.carId())));

        car.setBrand(command.brand());
        car.setModel(command.model());
        car.setYear(command.year());
        car.setPlate(command.plate());
        car.setFuelType(command.fuelType());
        return Optional.of(carRepository.save(car));
    }

    /** Deletes a Car by ID. */
    @Override
    public void handle(DeleteCarCommand command) {
        var car = carRepository.findById(command.carId())
                .orElseThrow(() -> new IllegalArgumentException(
                        String.format("Car %d not found", command.carId())));
        carRepository.delete(car);
    }
    
}