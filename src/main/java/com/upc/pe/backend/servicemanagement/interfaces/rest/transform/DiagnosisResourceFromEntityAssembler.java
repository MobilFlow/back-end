package com.upc.pe.backend.servicemanagement.interfaces.rest.transform;

import com.upc.pe.backend.servicemanagement.domain.model.aggregates.Diagnosis;
import com.upc.pe.backend.servicemanagement.interfaces.rest.resources.DiagnosisResource;

public class DiagnosisResourceFromEntityAssembler {
    public static DiagnosisResource toResourceFromEntity(Diagnosis entity) {
        return new DiagnosisResource(entity.getId(), entity.getServiceRequestId(), entity.getSummary(), entity.getRecommendedSpecialty(), entity.getCreatedAt());
    }
}
