package com.upc.pe.backend.reputation.interfaces.rest.transform;

import com.upc.pe.backend.reputation.domain.model.commands.CreateReviewCommand;
import com.upc.pe.backend.reputation.interfaces.rest.resources.CreateReviewResource;

public class CreateReviewCommandFromResourceAssembler {

    public static CreateReviewCommand toCommandFromResource(CreateReviewResource resource) {
        return new CreateReviewCommand(
                resource.content(),
                resource.mechanicId(),
                resource.driverId(),
                resource.serviceId(),
                resource.serviceFinished()
        );
    }
}
