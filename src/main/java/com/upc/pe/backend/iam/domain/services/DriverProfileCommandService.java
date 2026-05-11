package com.upc.pe.backend.iam.domain.services;

import com.upc.pe.backend.iam.domain.model.commands.*;
import com.upc.pe.backend.iam.domain.model.entities.Car;
import com.upc.pe.backend.iam.domain.model.entities.DriverProfile;

import java.util.Optional;

public interface DriverProfileCommandService {

    Optional<DriverProfile> handle(CreateDriverProfileCommand command);

    Optional<DriverProfile> handle(UpdateDriverProfileCommand command);

    Optional<Car> handle(RegisterCarCommand command);

    Optional<Car> handle(UpdateCarCommand command);

    void handle(DeleteCarCommand command);
}