package com.upc.pe.backend.servicemanagement.domain.services;

import com.upc.pe.backend.servicemanagement.interfaces.rest.resources.RecommendationResource;

import java.util.List;

public interface MatchingService {
    List<RecommendationResource> getRecommendations(Long serviceRequestId);
    List<RecommendationResource> getRecommendationsBySpecialty(String recommendedSpecialty);
}
