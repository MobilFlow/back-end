package com.upc.pe.backend.servicemanagement.interfaces.rest;

import com.upc.pe.backend.servicemanagement.domain.model.commands.GenerateDiagnosisCommand;
import com.upc.pe.backend.servicemanagement.domain.model.queries.GetDiagnosisByServiceRequestIdQuery;
import com.upc.pe.backend.servicemanagement.domain.services.DiagnosisCommandService;
import com.upc.pe.backend.servicemanagement.domain.services.DiagnosisQueryService;
import com.upc.pe.backend.servicemanagement.interfaces.rest.resources.CreateDiagnosisResource;
import com.upc.pe.backend.servicemanagement.interfaces.rest.resources.DiagnosisResource;
import com.upc.pe.backend.servicemanagement.interfaces.rest.transform.CreateDiagnosisCommandFromResourceAssembler;
import com.upc.pe.backend.servicemanagement.interfaces.rest.transform.DiagnosisResourceFromEntityAssembler;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/v1/service-requests/{serviceRequestId}/diagnosis", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "Service Requests")
public class ServiceRequestDiagnosisController {

    private final DiagnosisCommandService diagnosisCommandService;
    private final DiagnosisQueryService diagnosisQueryService;

    public ServiceRequestDiagnosisController(DiagnosisCommandService diagnosisCommandService, DiagnosisQueryService diagnosisQueryService) {
        this.diagnosisCommandService = diagnosisCommandService;
        this.diagnosisQueryService = diagnosisQueryService;
    }

    @PostMapping
    @Operation(summary = "Create manual diagnosis")
    public ResponseEntity<DiagnosisResource> createDiagnosis(@PathVariable Long serviceRequestId, @RequestBody CreateDiagnosisResource resource) {
        var createDiagnosisCommand = CreateDiagnosisCommandFromResourceAssembler.toCommandFromResource(serviceRequestId, resource);
        var diagnosis = diagnosisCommandService.handle(createDiagnosisCommand);
        if (diagnosis.isEmpty()) return ResponseEntity.badRequest().build();
        var diagnosisResource = DiagnosisResourceFromEntityAssembler.toResourceFromEntity(diagnosis.get());
        return new ResponseEntity<>(diagnosisResource, HttpStatus.CREATED);
    }

    @PostMapping("/generate")
    @Operation(summary = "Generate automatic diagnosis based on service request description")
    public ResponseEntity<DiagnosisResource> generateDiagnosis(@PathVariable Long serviceRequestId) {
        try {
            var generateDiagnosisCommand = new GenerateDiagnosisCommand(serviceRequestId);
            var diagnosis = diagnosisCommandService.handle(generateDiagnosisCommand);
            if (diagnosis.isEmpty()) return ResponseEntity.notFound().build();
            var diagnosisResource = DiagnosisResourceFromEntityAssembler.toResourceFromEntity(diagnosis.get());
            return new ResponseEntity<>(diagnosisResource, HttpStatus.CREATED);
        } catch (RuntimeException e) {
            if (e.getMessage().contains("already exists")) {
                return ResponseEntity.status(HttpStatus.CONFLICT).build();
            }
            throw e;
        }
    }

    @GetMapping
    @Operation(summary = "Get diagnosis by service request id")
    public ResponseEntity<DiagnosisResource> getDiagnosisByServiceRequestId(@PathVariable Long serviceRequestId) {
        var getDiagnosisByServiceRequestIdQuery = new GetDiagnosisByServiceRequestIdQuery(serviceRequestId);
        var diagnosis = diagnosisQueryService.handle(getDiagnosisByServiceRequestIdQuery);
        if (diagnosis.isEmpty()) return ResponseEntity.notFound().build();
        var diagnosisResource = DiagnosisResourceFromEntityAssembler.toResourceFromEntity(diagnosis.get());
        return ResponseEntity.ok(diagnosisResource);
    }
}
