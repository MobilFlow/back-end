package com.upc.pe.backend.servicemanagement.application.internal.queryservices;

import com.upc.pe.backend.servicemanagement.domain.model.aggregates.Diagnosis;
import com.upc.pe.backend.servicemanagement.domain.model.queries.GetDiagnosisByServiceRequestIdQuery;
import com.upc.pe.backend.servicemanagement.domain.services.DiagnosisQueryService;
import com.upc.pe.backend.servicemanagement.infrastructure.persistence.jpa.repositories.DiagnosisRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class DiagnosisQueryServiceImpl implements DiagnosisQueryService {

    private final DiagnosisRepository diagnosisRepository;

    public DiagnosisQueryServiceImpl(DiagnosisRepository diagnosisRepository) {
        this.diagnosisRepository = diagnosisRepository;
    }

    @Override
    public Optional<Diagnosis> handle(GetDiagnosisByServiceRequestIdQuery query) {
        return diagnosisRepository.findByServiceRequestId(query.serviceRequestId());
    }
}
