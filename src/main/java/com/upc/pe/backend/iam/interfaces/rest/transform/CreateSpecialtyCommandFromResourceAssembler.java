package com.upc.pe.backend.iam.interfaces.rest.transform;

import com.upc.pe.backend.iam.domain.model.commands.CreateSpecialtyCommand;
import com.upc.pe.backend.iam.interfaces.rest.resources.CreateSpecialtyResource;

public class CreateSpecialtyCommandFromResourceAssembler {

    public static CreateSpecialtyCommand toCommandFromResource(
            CreateSpecialtyResource resource
    ) {
        return new CreateSpecialtyCommand(
                resource.name()
        );
    }
}