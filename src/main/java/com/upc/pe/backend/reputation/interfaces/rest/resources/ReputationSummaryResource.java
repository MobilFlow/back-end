package com.upc.pe.backend.reputation.interfaces.rest.resources;

public record ReputationSummaryResource(
        Double averageRating,
        Integer totalRatings,
        Integer totalReviews
) {
}
