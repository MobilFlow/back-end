package com.upc.pe.backend.iam.interfaces.rest.transform;


import com.upc.pe.backend.iam.domain.model.commands.CreateDriverProfileCommand;
import com.upc.pe.backend.iam.interfaces.rest.resources.CreateDriverProfileResource;

public class CreateDriverProfileCommandFromResourceAssembler {

    public static CreateDriverProfileCommand toCommandFromResource(
            CreateDriverProfileResource resource
    ) {
        return new CreateDriverProfileCommand(
                resource.userId()
        );
    }
}