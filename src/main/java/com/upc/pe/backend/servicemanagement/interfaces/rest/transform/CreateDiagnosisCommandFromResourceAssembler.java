package com.upc.pe.backend.servicemanagement.interfaces.rest.transform;

import com.upc.pe.backend.servicemanagement.domain.model.commands.CreateDiagnosisCommand;
import com.upc.pe.backend.servicemanagement.interfaces.rest.resources.CreateDiagnosisResource;

public class CreateDiagnosisCommandFromResourceAssembler {
    public static CreateDiagnosisCommand toCommandFromResource(Long serviceRequestId, CreateDiagnosisResource resource) {
        return new CreateDiagnosisCommand(serviceRequestId, resource.summary(), resource.recommendedSpecialty());
    }
}
