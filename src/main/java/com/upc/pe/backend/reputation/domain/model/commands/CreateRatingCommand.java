package com.upc.pe.backend.reputation.domain.model.commands;

public record CreateRatingCommand(
        Integer score,
        Long mechanicId,
        Long driverId,
        Long serviceId,
        Boolean serviceFinished
) {
}
