package com.upc.pe.backend.reputation.domain.model.commands;

public record CreateReviewCommand(
        String content,
        Long mechanicId,
        Long driverId,
        Long serviceId,
        Boolean serviceFinished
) {
}
