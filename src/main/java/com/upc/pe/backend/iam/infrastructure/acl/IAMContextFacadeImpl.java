package com.upc.pe.backend.iam.infrastructure.acl;

import com.upc.pe.backend.iam.infrastructure.persistence.jpa.repositories.CarRepository;
import com.upc.pe.backend.iam.infrastructure.persistence.jpa.repositories.DriverProfileRepository;
import com.upc.pe.backend.iam.infrastructure.persistence.jpa.repositories.MechanicProfileRepository;
import org.springframework.stereotype.Service;

@Service
public class IAMContextFacadeImpl implements IAMContextFacade {

    private final MechanicProfileRepository mechanicProfileRepository;
    private final DriverProfileRepository driverProfileRepository;
    private final CarRepository carRepository;

    public IAMContextFacadeImpl(
            MechanicProfileRepository mechanicProfileRepository,
            DriverProfileRepository driverProfileRepository,
            CarRepository carRepository
    ) {
        this.mechanicProfileRepository = mechanicProfileRepository;
        this.driverProfileRepository = driverProfileRepository;
        this.carRepository = carRepository;
    }

    @Override
    public boolean existsMechanicProfileById(Long mechanicProfileId) {
        return mechanicProfileRepository.existsById(mechanicProfileId);
    }

    @Override
    public boolean existsDriverProfileById(Long driverProfileId) {
        return driverProfileRepository.existsById(driverProfileId);
    }

    @Override
    public boolean existsCarById(Long carId) {
        return carRepository.existsById(carId);
    }
}