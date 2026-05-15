package com.upc.pe.backend.reputation.domain.model.commands;

public record UpdateRatingCommand(
        Long ratingId,
        Integer score,
        Boolean serviceFinished
) {
}
