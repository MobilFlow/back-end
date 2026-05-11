package com.upc.pe.backend.reputation.interfaces.rest.resources;

import java.util.Date;

public record RatingResource(
        Long id,
        Integer score,
        Long mechanicId,
        Long driverId,
        Long serviceId,
        Date createdAt,
        Boolean edited
) {
}
