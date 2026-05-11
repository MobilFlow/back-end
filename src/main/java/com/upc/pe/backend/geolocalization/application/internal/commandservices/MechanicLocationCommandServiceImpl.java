package com.upc.pe.backend.geolocalization.application.internal.commandservices;

import com.upc.pe.backend.geolocalization.domain.model.aggregates.MechanicLocation;
import com.upc.pe.backend.geolocalization.domain.model.commands.RegisterMechanicLocationCommand;
import com.upc.pe.backend.geolocalization.domain.services.MechanicLocationCommandService;
import com.upc.pe.backend.geolocalization.infrastructure.persistance.jpa.repositories.MechanicLocationRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class MechanicLocationCommandServiceImpl implements MechanicLocationCommandService {

    private final MechanicLocationRepository mechanicLocationRepository;

    public MechanicLocationCommandServiceImpl(
            MechanicLocationRepository mechanicLocationRepository
    ) {
        this.mechanicLocationRepository = mechanicLocationRepository;
    }

    @Override
    @Transactional
    public Optional<MechanicLocation> handle(RegisterMechanicLocationCommand command) {

        var existingLocation =
                mechanicLocationRepository.findByMechanicId(command.mechanicId());

        if (existingLocation.isPresent()) {

            var location = existingLocation.get();

            location.updateLocation(
                    command.latitude(),
                    command.longitude(),
                    command.addressText()
            );

            return Optional.of(mechanicLocationRepository.save(location));
        }

        var location = new MechanicLocation(
                command.mechanicId(),
                command.latitude(),
                command.longitude(),
                command.addressText()
        );

        return Optional.of(mechanicLocationRepository.save(location));
    }
}