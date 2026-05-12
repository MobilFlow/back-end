package com.upc.pe.backend.servicemanagement.interfaces.rest;

import com.upc.pe.backend.servicemanagement.domain.model.queries.*;
import com.upc.pe.backend.servicemanagement.domain.services.ServiceRequestCommandService;
import com.upc.pe.backend.servicemanagement.domain.services.ServiceRequestQueryService;
import com.upc.pe.backend.servicemanagement.interfaces.rest.resources.ConfirmServiceCompletionResource;
import com.upc.pe.backend.servicemanagement.interfaces.rest.resources.CreateServiceRequestResource;
import com.upc.pe.backend.servicemanagement.interfaces.rest.resources.ServiceRequestResource;
import com.upc.pe.backend.servicemanagement.interfaces.rest.transform.ConfirmServiceCompletionCommandFromResourceAssembler;
import com.upc.pe.backend.servicemanagement.interfaces.rest.transform.CreateServiceRequestCommandFromResourceAssembler;
import com.upc.pe.backend.servicemanagement.interfaces.rest.transform.ServiceRequestResourceFromEntityAssembler;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping(value = "/api/v1/service-requests", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "Service Requests", description = "Available Service Request Endpoints")
public class ServiceRequestsController {

    private final ServiceRequestCommandService serviceRequestCommandService;
    private final ServiceRequestQueryService serviceRequestQueryService;

    public ServiceRequestsController(
            ServiceRequestCommandService serviceRequestCommandService,
            ServiceRequestQueryService serviceRequestQueryService
    ) {
        this.serviceRequestCommandService = serviceRequestCommandService;
        this.serviceRequestQueryService = serviceRequestQueryService;
    }

    @PostMapping
    @Operation(summary = "Request a new service")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Service requested successfully."),
            @ApiResponse(responseCode = "400", description = "Invalid request.")
    })
    public ResponseEntity<ServiceRequestResource> requestService(
            @RequestBody CreateServiceRequestResource resource
    ) {
        var command = CreateServiceRequestCommandFromResourceAssembler
                .toCommandFromResource(resource);

        var serviceRequest = serviceRequestCommandService.handle(command);

        if (serviceRequest.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }

        var response = ServiceRequestResourceFromEntityAssembler
                .toResourceFromEntity(serviceRequest.get());

        return ResponseEntity.ok(response);
    }

    @GetMapping("/{serviceId}")
    @Operation(summary = "Get service by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Service retrieved."),
            @ApiResponse(responseCode = "404", description = "Service not found.")
    })
    public ResponseEntity<ServiceRequestResource> getServiceById(
            @PathVariable Long serviceId
    ) {
        var query = new GetServiceByIdQuery(serviceId);

        var serviceRequest = serviceRequestQueryService.handle(query);

        if (serviceRequest.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        var response = ServiceRequestResourceFromEntityAssembler
                .toResourceFromEntity(serviceRequest.get());

        return ResponseEntity.ok(response);
    }

    @GetMapping("/driver/{driverProfileId}")
    @Operation(summary = "Get services by driver profile ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Services retrieved.")
    })
    public ResponseEntity<List<ServiceRequestResource>> getServicesByDriverProfileId(
            @PathVariable Long driverProfileId
    ) {
        var query = new GetServicesByDriverProfileIdQuery(driverProfileId);

        var services = serviceRequestQueryService.handle(query);

        var resources = services.stream()
                .map(ServiceRequestResourceFromEntityAssembler::toResourceFromEntity)
                .collect(Collectors.toList());

        return ResponseEntity.ok(resources);
    }

    @GetMapping("/mechanic/{mechanicProfileId}")
    @Operation(summary = "Get services by mechanic profile ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Services retrieved.")
    })
    public ResponseEntity<List<ServiceRequestResource>> getServicesByMechanicProfileId(
            @PathVariable Long mechanicProfileId
    ) {
        var query = new GetServicesByMechanicProfileIdQuery(mechanicProfileId);

        var services = serviceRequestQueryService.handle(query);

        var resources = services.stream()
                .map(ServiceRequestResourceFromEntityAssembler::toResourceFromEntity)
                .collect(Collectors.toList());

        return ResponseEntity.ok(resources);
    }

    @GetMapping("/history/{driverProfileId}")
    @Operation(summary = "Get service history for a driver")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "History retrieved.")
    })
    public ResponseEntity<List<ServiceRequestResource>> getServiceHistory(
            @PathVariable Long driverProfileId
    ) {
        var query = new GetServiceHistoryQuery(driverProfileId);

        var services = serviceRequestQueryService.handle(query);

        var resources = services.stream()
                .map(ServiceRequestResourceFromEntityAssembler::toResourceFromEntity)
                .collect(Collectors.toList());

        return ResponseEntity.ok(resources);
    }

    @PutMapping("/{serviceId}/confirm")
    @Operation(summary = "Confirm service completion")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Completion confirmed."),
            @ApiResponse(responseCode = "400", description = "Invalid request."),
            @ApiResponse(responseCode = "404", description = "Service not found.")
    })
    public ResponseEntity<ServiceRequestResource> confirmCompletion(
            @PathVariable Long serviceId,
            @RequestBody ConfirmServiceCompletionResource resource
    ) {
        var command = ConfirmServiceCompletionCommandFromResourceAssembler
                .toCommandFromResource(serviceId, resource);

        var serviceRequest = serviceRequestCommandService.handle(command);

        if (serviceRequest.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        var response = ServiceRequestResourceFromEntityAssembler
                .toResourceFromEntity(serviceRequest.get());

        return ResponseEntity.ok(response);
    }

    @PutMapping("/{serviceId}/cancel")
    @Operation(summary = "Cancel a service")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Service cancelled."),
            @ApiResponse(responseCode = "400", description = "Invalid request."),
            @ApiResponse(responseCode = "404", description = "Service not found.")
    })
    public ResponseEntity<ServiceRequestResource> cancelService(
            @PathVariable Long serviceId,
            @RequestBody ConfirmServiceCompletionResource resource
    ) {
        var command = new com.upc.pe.backend.servicemanagement.domain.model.commands.CancelServiceCommand(
                serviceId,
                resource.actorProfileId(),
                resource.role()
        );

        var serviceRequest = serviceRequestCommandService.handle(command);

        if (serviceRequest.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        var response = ServiceRequestResourceFromEntityAssembler
                .toResourceFromEntity(serviceRequest.get());

        return ResponseEntity.ok(response);
    }
}
