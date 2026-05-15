package com.upc.pe.backend.reputation.interfaces.rest.resources;

public record CreateReviewResource(
        String content,
        Long mechanicId,
        Long driverId,
        Long serviceId,
        Boolean serviceFinished
) {
}
