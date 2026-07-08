package com.upc.pe.backend.servicemanagement.domain.services;

import com.upc.pe.backend.servicemanagement.domain.model.aggregates.Diagnosis;
import com.upc.pe.backend.servicemanagement.domain.model.commands.CreateDiagnosisCommand;
import com.upc.pe.backend.servicemanagement.domain.model.commands.GenerateDiagnosisCommand;

import java.util.Optional;

public interface DiagnosisCommandService {
    Optional<Diagnosis> handle(CreateDiagnosisCommand command);
    Optional<Diagnosis> handle(GenerateDiagnosisCommand command);
}
