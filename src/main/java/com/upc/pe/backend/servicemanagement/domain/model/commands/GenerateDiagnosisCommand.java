package com.upc.pe.backend.servicemanagement.domain.model.commands;

public record GenerateDiagnosisCommand(Long serviceRequestId) {
    public GenerateDiagnosisCommand {
        if (serviceRequestId == null || serviceRequestId <= 0) {
            throw new IllegalArgumentException("ServiceRequestId is required and must be greater than zero");
        }
    }
}
