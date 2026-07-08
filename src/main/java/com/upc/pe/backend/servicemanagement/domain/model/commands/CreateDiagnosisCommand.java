package com.upc.pe.backend.servicemanagement.domain.model.commands;

public record CreateDiagnosisCommand(Long serviceRequestId, String summary, String recommendedSpecialty) {
    public CreateDiagnosisCommand {
        if (serviceRequestId == null || serviceRequestId <= 0) {
            throw new IllegalArgumentException("ServiceRequestId is required and must be greater than zero");
        }
        if (summary == null || summary.isBlank()) {
            throw new IllegalArgumentException("Summary is required");
        }
        if (recommendedSpecialty == null || recommendedSpecialty.isBlank()) {
            throw new IllegalArgumentException("RecommendedSpecialty is required");
        }
    }
}
