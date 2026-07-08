package com.upc.pe.backend.servicemanagement.domain.model.queries;

public record GetDiagnosisByServiceRequestIdQuery(Long serviceRequestId) {
    public GetDiagnosisByServiceRequestIdQuery {
        if (serviceRequestId == null || serviceRequestId <= 0) {
            throw new IllegalArgumentException("ServiceRequestId is required and must be greater than zero");
        }
    }
}
