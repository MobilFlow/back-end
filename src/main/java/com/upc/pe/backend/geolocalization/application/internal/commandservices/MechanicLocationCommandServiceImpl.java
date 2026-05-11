package com.upc.pe.backend.geolocalization.application.internal.commandservices;

import com.upc.pe.backend.geolocalization.domain.model.aggregates.MechanicLocation;
import com.upc.pe.backend.geolocalization.domain.model.commands.RegisterMechanicLocationCommand;
import com.upc.pe.backend.geolocalization.domain.services.MechanicLocationCommandService;
import com.upc.pe.backend.geolocalization.infrastructure.persistance.jpa.repositories.MechanicLocationRepository;
import com.upc.pe.backend.iam.infrastructure.acl.IAMContextFacade;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class MechanicLocationCommandServiceImpl implements MechanicLocationCommandService {

    private final MechanicLocationRepository mechanicLocationRepository;
    private final IAMContextFacade iamContextFacade;

    public MechanicLocationCommandServiceImpl(
            MechanicLocationRepository mechanicLocationRepository,
            IAMContextFacade iamContextFacade
    ) {
        this.mechanicLocationRepository = mechanicLocationRepository;
        this.iamContextFacade = iamContextFacade;
    }
    @Override
    @Transactional
    public Optional<MechanicLocation> handle(RegisterMechanicLocationCommand command) {

        if (!iamContextFacade.existsMechanicProfileById(command.mechanicId())) {
            throw new IllegalArgumentException(
                    String.format(
                            "MechanicProfile %d does not exist",
                            command.mechanicId()
                    )
            );
        }

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