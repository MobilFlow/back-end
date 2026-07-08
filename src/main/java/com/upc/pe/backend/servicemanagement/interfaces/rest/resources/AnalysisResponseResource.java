package com.upc.pe.backend.servicemanagement.interfaces.rest.resources;

import java.util.List;

public record AnalysisResponseResource(
    DiagnosisResource diagnosis,
    List<RecommendationResource> recommendations
) {}
