package com.upc.pe.backend.servicemanagement.interfaces.rest.resources;

public record RecommendationResource(
    Long mechanicProfileId,
    String workshopName,
    String matchedSpecialty,
    Double averageScore,
    Integer ratingsCount,
    Double score
) {}
