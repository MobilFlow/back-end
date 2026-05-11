package com.upc.pe.backend.reputation.interfaces.rest.resources;

public record UpdateReviewResource(
        String content,
        Boolean serviceFinished
) {
}
