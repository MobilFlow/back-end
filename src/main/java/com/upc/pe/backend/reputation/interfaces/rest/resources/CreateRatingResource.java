package com.upc.pe.backend.reputation.interfaces.rest.resources;

public record CreateRatingResource(
        Integer score,
        Long mechanicId,
        Long driverId,
        Long serviceId,
        Boolean serviceFinished
) {
}
