package com.upc.pe.backend.reputation.interfaces.rest.transform;

import com.upc.pe.backend.reputation.domain.model.commands.CreateRatingCommand;
import com.upc.pe.backend.reputation.interfaces.rest.resources.CreateRatingResource;

public class CreateRatingCommandFromResourceAssembler {

    public static CreateRatingCommand toCommandFromResource(CreateRatingResource resource) {
        return new CreateRatingCommand(
                resource.score(),
                resource.mechanicId(),
                resource.driverId(),
                resource.serviceId(),
                resource.serviceFinished()
        );
    }
}
