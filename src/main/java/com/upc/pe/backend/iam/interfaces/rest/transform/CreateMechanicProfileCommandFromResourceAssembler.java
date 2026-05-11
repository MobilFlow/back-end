package com.upc.pe.backend.iam.interfaces.rest.transform;

import com.upc.pe.backend.iam.domain.model.commands.CreateMechanicProfileCommand;
import com.upc.pe.backend.iam.interfaces.rest.resources.CreateMechanicProfileResource;

public class CreateMechanicProfileCommandFromResourceAssembler {

    public static CreateMechanicProfileCommand toCommandFromResource(
            CreateMechanicProfileResource resource
    ) {
        return new CreateMechanicProfileCommand(
                resource.userId(),
                resource.description(),
                resource.workshopName(),
                resource.workshopAddress()
        );
    }
}