package com.upc.pe.backend.reputation.domain.model.dtos;

public record ReputationSummaryDto(
        Double averageRating,
        Integer totalRatings,
        Integer totalReviews
) {
}
