package com.upc.pe.backend.geolocalization.domain.services;

import com.upc.pe.backend.geolocalization.domain.model.aggregates.MechanicLocation;
import com.upc.pe.backend.geolocalization.domain.model.commands.RegisterMechanicLocationCommand;

import java.util.Optional;

public interface MechanicLocationCommandService {

    Optional<MechanicLocation> handle(RegisterMechanicLocationCommand command);

}