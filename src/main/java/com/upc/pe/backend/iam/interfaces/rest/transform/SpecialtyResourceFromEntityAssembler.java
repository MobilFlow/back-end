package com.upc.pe.backend.iam.interfaces.rest.transform;

import com.upc.pe.backend.iam.domain.model.entities.Specialty;
import com.upc.pe.backend.iam.interfaces.rest.resources.SpecialtyResource;

public class SpecialtyResourceFromEntityAssembler {

    public static SpecialtyResource toResourceFromEntity(Specialty entity) {
        return new SpecialtyResource(
                entity.getId(),
                entity.getName()
        );
    }
}