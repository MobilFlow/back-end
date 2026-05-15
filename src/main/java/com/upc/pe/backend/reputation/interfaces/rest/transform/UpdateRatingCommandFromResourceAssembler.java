package com.upc.pe.backend.reputation.interfaces.rest.transform;

import com.upc.pe.backend.reputation.domain.model.commands.UpdateRatingCommand;
import com.upc.pe.backend.reputation.interfaces.rest.resources.UpdateRatingResource;

public class UpdateRatingCommandFromResourceAssembler {

    public static UpdateRatingCommand toCommandFromResource(
            Long ratingId,
            UpdateRatingResource resource
    ) {
        return new UpdateRatingCommand(
                ratingId,
                resource.score(),
                resource.serviceFinished()
        );
    }
}
