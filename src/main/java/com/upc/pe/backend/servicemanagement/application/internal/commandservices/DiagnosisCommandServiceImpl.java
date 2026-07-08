package com.upc.pe.backend.servicemanagement.application.internal.commandservices;

import com.upc.pe.backend.servicemanagement.domain.model.aggregates.Diagnosis;
import com.upc.pe.backend.servicemanagement.domain.model.commands.CreateDiagnosisCommand;
import com.upc.pe.backend.servicemanagement.domain.model.commands.GenerateDiagnosisCommand;
import com.upc.pe.backend.servicemanagement.domain.services.DiagnosisCommandService;
import com.upc.pe.backend.servicemanagement.domain.services.DiagnosisGeneratorService;
import com.upc.pe.backend.servicemanagement.infrastructure.persistence.jpa.repositories.DiagnosisRepository;
import com.upc.pe.backend.servicemanagement.infrastructure.persistence.jpa.repositories.ServiceRequestRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class DiagnosisCommandServiceImpl implements DiagnosisCommandService {

    private final DiagnosisRepository diagnosisRepository;
    private final ServiceRequestRepository serviceRequestRepository;
    private final DiagnosisGeneratorService diagnosisGeneratorService;

    public DiagnosisCommandServiceImpl(DiagnosisRepository diagnosisRepository, ServiceRequestRepository serviceRequestRepository, DiagnosisGeneratorService diagnosisGeneratorService) {
        this.diagnosisRepository = diagnosisRepository;
        this.serviceRequestRepository = serviceRequestRepository;
        this.diagnosisGeneratorService = diagnosisGeneratorService;
    }

    @Override
    public Optional<Diagnosis> handle(CreateDiagnosisCommand command) {
        if (!serviceRequestRepository.existsById(command.serviceRequestId())) {
            throw new IllegalArgumentException("ServiceRequest with ID " + command.serviceRequestId() + " does not exist");
        }
        if (diagnosisRepository.existsByServiceRequestId(command.serviceRequestId())) {
            throw new IllegalArgumentException("Diagnosis for ServiceRequest with ID " + command.serviceRequestId() + " already exists");
        }
        var diagnosis = new Diagnosis(command);
        diagnosisRepository.save(diagnosis);
        return Optional.of(diagnosis);
    }

    @Override
    public Optional<Diagnosis> handle(GenerateDiagnosisCommand command) {
        var serviceRequest = serviceRequestRepository.findById(command.serviceRequestId());
        if (serviceRequest.isEmpty()) {
            return Optional.empty();
        }

        if (diagnosisRepository.existsByServiceRequestId(command.serviceRequestId())) {
            throw new RuntimeException("Diagnosis for ServiceRequest with ID " + command.serviceRequestId() + " already exists");
        }

        String description = serviceRequest.get().getDescription();
        String recommendedSpecialty = diagnosisGeneratorService.recommendSpecialty(description);
        String summary = diagnosisGeneratorService.generateSummary(description, recommendedSpecialty);

        var createCommand = new CreateDiagnosisCommand(command.serviceRequestId(), summary, recommendedSpecialty);
        return handle(createCommand);
    }
}
