package com.upc.pe.backend.reputation.interfaces.rest.resources;

public record UpdateRatingResource(
        Integer score,
        Boolean serviceFinished
) {
}
