package com.upc.pe.backend.reputation.interfaces.rest.transform;

import com.upc.pe.backend.reputation.domain.model.aggregates.Rating;
import com.upc.pe.backend.reputation.interfaces.rest.resources.RatingResource;

public class RatingResourceFromEntityAssembler {

    public static RatingResource toResourceFromEntity(Rating entity) {
        return new RatingResource(
                entity.getId(),
                entity.getScore(),
                entity.getMechanicId(),
                entity.getDriverId(),
                entity.getServiceId(),
                entity.getCreatedAt(),
                entity.getEdited()
        );
    }
}
