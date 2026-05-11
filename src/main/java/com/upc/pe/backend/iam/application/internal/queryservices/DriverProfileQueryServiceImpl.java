package com.upc.pe.backend.iam.application.internal.queryservices;


import com.upc.pe.backend.iam.domain.model.entities.Car;
import com.upc.pe.backend.iam.domain.model.entities.DriverProfile;
import com.upc.pe.backend.iam.domain.model.queries.GetCarByIdQuery;
import com.upc.pe.backend.iam.domain.model.queries.GetCarsByDriverProfileIdQuery;
import com.upc.pe.backend.iam.domain.model.queries.GetDriverProfileByUserIdQuery;
import com.upc.pe.backend.iam.domain.services.DriverProfileQueryService;
import com.upc.pe.backend.iam.infrastructure.persistence.jpa.repositories.CarRepository;
import com.upc.pe.backend.iam.infrastructure.persistence.jpa.repositories.DriverProfileRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DriverProfileQueryServiceImpl implements DriverProfileQueryService {

    private final DriverProfileRepository driverProfileRepository;
    private final CarRepository carRepository;

    public DriverProfileQueryServiceImpl(
            DriverProfileRepository driverProfileRepository,
            CarRepository carRepository
    ) {
        this.driverProfileRepository = driverProfileRepository;
        this.carRepository = carRepository;
    }

    @Override
    public Optional<Car> handle(GetCarByIdQuery query) {
        return carRepository.findById(query.carId());
    }

    @Override
    public List<Car> handle(GetCarsByDriverProfileIdQuery query) {

        var profile = driverProfileRepository.findById(query.driverProfileId())
                .orElseThrow(() -> new IllegalArgumentException(
                        String.format("DriverProfile %d not found", query.driverProfileId())
                ));

        return profile.getCars();
    }

    @Override
    public Optional<DriverProfile> handle(GetDriverProfileByUserIdQuery query) {
        return driverProfileRepository.findByUserId(query.userId());
    }
}