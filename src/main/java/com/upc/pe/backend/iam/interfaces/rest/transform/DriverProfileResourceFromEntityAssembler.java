package com.upc.pe.backend.iam.interfaces.rest.transform;

import com.upc.pe.backend.iam.domain.model.entities.DriverProfile;
import com.upc.pe.backend.iam.interfaces.rest.resources.DriverProfileResource;

public class DriverProfileResourceFromEntityAssembler {

    public static DriverProfileResource toResourceFromEntity(DriverProfile entity) {

        var cars = entity.getCars()
                .stream()
                .map(CarResourceFromEntityAssembler::toResourceFromEntity)
                .toList();

        return new DriverProfileResource(
                entity.getId(),
                entity.getUserId(),
                cars
        );
    }
}