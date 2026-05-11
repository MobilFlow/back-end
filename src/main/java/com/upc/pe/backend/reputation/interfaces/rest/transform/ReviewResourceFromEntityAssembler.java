package com.upc.pe.backend.reputation.interfaces.rest.transform;

import com.upc.pe.backend.reputation.domain.model.aggregates.Review;
import com.upc.pe.backend.reputation.interfaces.rest.resources.ReviewResource;

public class ReviewResourceFromEntityAssembler {

    public static ReviewResource toResourceFromEntity(Review entity) {
        return new ReviewResource(
                entity.getId(),
                entity.getContent(),
                entity.getMechanicId(),
                entity.getDriverId(),
                entity.getServiceId(),
                entity.getCreatedAt(),
                entity.getEdited()
        );
    }
}
