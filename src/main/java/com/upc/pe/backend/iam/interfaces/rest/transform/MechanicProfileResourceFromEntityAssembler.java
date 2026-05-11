package com.upc.pe.backend.iam.interfaces.rest.transform;

import com.upc.pe.backend.iam.domain.model.entities.MechanicProfile;
import com.upc.pe.backend.iam.interfaces.rest.resources.MechanicProfileResource;

public class MechanicProfileResourceFromEntityAssembler {

    public static MechanicProfileResource toResourceFromEntity(
            MechanicProfile entity
    ) {

        var specialties = entity.getSpecialties()
                .stream()
                .map(SpecialtyResourceFromEntityAssembler::toResourceFromEntity)
                .toList();

        return new MechanicProfileResource(
                entity.getId(),
                entity.getUserId(),
                entity.getDescription(),
                entity.getWorkshopName(),
                entity.getWorkshopAddress(),
                specialties
        );
    }
}