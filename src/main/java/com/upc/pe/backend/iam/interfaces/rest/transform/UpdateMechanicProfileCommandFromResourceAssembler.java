package com.upc.pe.backend.iam.interfaces.rest.transform;

import com.upc.pe.backend.iam.domain.model.commands.UpdateMechanicProfileCommand;
import com.upc.pe.backend.iam.interfaces.rest.resources.UpdateMechanicProfileResource;

public class UpdateMechanicProfileCommandFromResourceAssembler {

    public static UpdateMechanicProfileCommand toCommandFromResource(
            Long mechanicProfileId,
            UpdateMechanicProfileResource resource
    ) {
        return new UpdateMechanicProfileCommand(
                mechanicProfileId,
                resource.description(),
                resource.workshopName(),
                resource.workshopAddress()
        );
    }
}