package com.upc.pe.backend.servicemanagement.domain.services;

public interface DiagnosisGeneratorService {
    String generateSummary(String description, String recommendedSpecialty);
    String recommendSpecialty(String description);
}
