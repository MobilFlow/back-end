package com.upc.pe.backend.reputation.interfaces.rest.resources;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public record UpdateRatingResource(
        @NotNull(message = "Rating score is required")
        @Min(value = 1, message = "Rating score must be between 1 and 5")
        @Max(value = 5, message = "Rating score must be between 1 and 5")
        Integer score,
        Boolean serviceFinished
) {
}
