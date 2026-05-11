package com.upc.pe.backend.reputation.domain.model.commands;

public record UpdateReviewCommand(
        Long reviewId,
        String content,
        Boolean serviceFinished
) {
}
