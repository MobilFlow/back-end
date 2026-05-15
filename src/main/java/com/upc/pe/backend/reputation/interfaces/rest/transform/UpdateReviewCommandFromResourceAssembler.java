package com.upc.pe.backend.reputation.interfaces.rest.transform;

import com.upc.pe.backend.reputation.domain.model.commands.UpdateReviewCommand;
import com.upc.pe.backend.reputation.interfaces.rest.resources.UpdateReviewResource;

public class UpdateReviewCommandFromResourceAssembler {

    public static UpdateReviewCommand toCommandFromResource(
            Long reviewId,
            UpdateReviewResource resource
    ) {
        return new UpdateReviewCommand(
                reviewId,
                resource.content(),
                resource.serviceFinished()
        );
    }
}
