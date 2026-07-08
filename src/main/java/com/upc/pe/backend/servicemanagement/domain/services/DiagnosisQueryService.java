package com.upc.pe.backend.servicemanagement.domain.services;

import com.upc.pe.backend.servicemanagement.domain.model.aggregates.Diagnosis;
import com.upc.pe.backend.servicemanagement.domain.model.queries.GetDiagnosisByServiceRequestIdQuery;

import java.util.Optional;

public interface DiagnosisQueryService {
    Optional<Diagnosis> handle(GetDiagnosisByServiceRequestIdQuery query);
}
