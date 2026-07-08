package com.upc.pe.backend.iam.infrastructure.acl;

import com.upc.pe.backend.iam.domain.model.entities.Specialty;
import com.upc.pe.backend.iam.infrastructure.persistence.jpa.repositories.CarRepository;
import com.upc.pe.backend.iam.infrastructure.persistence.jpa.repositories.DriverProfileRepository;
import com.upc.pe.backend.iam.infrastructure.persistence.jpa.repositories.MechanicProfileRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

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

    @Override
    public List<MechanicMatchingCandidateDTO> getAllMechanicMatchingCandidates() {
        return mechanicProfileRepository.findAll().stream()
                .map(mp -> new MechanicMatchingCandidateDTO(
                        mp.getId(),
                        mp.getWorkshopName(),
                        mp.getSpecialties().stream().map(Specialty::getName).collect(Collectors.toList())
                ))
                .collect(Collectors.toList());
    }
}