package com.upc.pe.backend.iam.interfaces.rest.transform;

import com.upc.pe.backend.iam.domain.model.commands.AddSpecialtyToMechanicCommand;
import com.upc.pe.backend.iam.interfaces.rest.resources.AddSpecialtyToMechanicResource;

public class AddSpecialtyToMechanicCommandFromResourceAssembler {

    public static AddSpecialtyToMechanicCommand toCommandFromResource(
            Long mechanicProfileId,
            AddSpecialtyToMechanicResource resource
    ) {
        return new AddSpecialtyToMechanicCommand(
                mechanicProfileId,
                resource.specialtyId()
        );
    }
}