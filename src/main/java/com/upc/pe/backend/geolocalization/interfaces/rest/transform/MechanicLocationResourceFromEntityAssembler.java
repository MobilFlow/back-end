package com.upc.pe.backend.geolocalization.interfaces.rest.transform;

import com.upc.pe.backend.geolocalization.domain.model.aggregates.MechanicLocation;
import com.upc.pe.backend.geolocalization.interfaces.rest.resources.MechanicLocationResource;

public class MechanicLocationResourceFromEntityAssembler {

    public static MechanicLocationResource toResourceFromEntity(
            MechanicLocation entity
    ) {

        return new MechanicLocationResource(
                entity.getId(),
                entity.getMechanicId(),
                entity.getLatitude(),
                entity.getLongitude(),
                entity.getAddressText()
        );
    }
}