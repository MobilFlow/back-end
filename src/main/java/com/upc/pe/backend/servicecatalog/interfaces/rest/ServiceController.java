package com.upc.pe.backend.servicecatalog.interfaces.rest;

import com.upc.pe.backend.servicecatalog.domain.model.commands.AddTagToServiceCommand;
import com.upc.pe.backend.servicecatalog.domain.model.commands.DeactivateServiceCommand;
import com.upc.pe.backend.servicecatalog.domain.model.queries.GetAllServicesQuery;
import com.upc.pe.backend.servicecatalog.domain.model.queries.GetServiceByIdQuery;
import com.upc.pe.backend.servicecatalog.domain.services.ServiceCommandService;
import com.upc.pe.backend.servicecatalog.domain.services.ServiceQueryService;
import com.upc.pe.backend.servicecatalog.interfaces.rest.resources.AddTagToServiceResource;
import com.upc.pe.backend.servicecatalog.interfaces.rest.resources.PublishServiceResource;
import com.upc.pe.backend.servicecatalog.interfaces.rest.resources.ServiceResource;
import com.upc.pe.backend.servicecatalog.interfaces.rest.resources.UpdateServiceResource;
import com.upc.pe.backend.servicecatalog.interfaces.rest.transform.PublishServiceCommandFromResourceAssembler;
import com.upc.pe.backend.servicecatalog.interfaces.rest.transform.ServiceResourceFromEntityAssembler;
import com.upc.pe.backend.servicecatalog.interfaces.rest.transform.UpdateServiceCommandFromResourceAssembler;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

/**
 * ServiceController
 *
 * This controller is responsible for handling
 * service-related endpoints inside the
 * ServiceCatalog bounded context.
 *
 * It provides functionality for:
 * - Publishing services
 * - Updating services
 * - Deactivating services
 * - Retrieving services
 * - Associating tags
 */
@RestController
@RequestMapping(
        value = "/api/v1/services",
        produces = MediaType.APPLICATION_JSON_VALUE
)
@Tag(name = "Services", description = "Available Service Endpoints")
public class ServiceController {

    private final ServiceCommandService serviceCommandService;
    private final ServiceQueryService serviceQueryService;

    public ServiceController(
            ServiceCommandService serviceCommandService,
            ServiceQueryService serviceQueryService
    ) {
        this.serviceCommandService = serviceCommandService;
        this.serviceQueryService = serviceQueryService;
    }

    /**
     * Publishes a new service.
     *
     * @param resource publish service resource
     * @return created service resource
     */
    @PostMapping
    @Operation(
            summary = "Publish service",
            description = "Publishes a new mechanic service."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Service published successfully."),
            @ApiResponse(responseCode = "400", description = "Invalid request.")
    })
    public ResponseEntity<ServiceResource> publishService(
            @RequestBody PublishServiceResource resource
    ) {

        var command =
                PublishServiceCommandFromResourceAssembler
                        .toCommandFromResource(resource);

        var service = serviceCommandService.handle(command);

        if (service.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }

        var serviceResource =
                ServiceResourceFromEntityAssembler
                        .toResourceFromEntity(service.get());

        return new ResponseEntity<>(
                serviceResource,
                HttpStatus.CREATED
        );
    }

    /**
     * Retrieves all services.
     *
     * @return list of services
     */
    @GetMapping
    @Operation(
            summary = "Get all services",
            description = "Retrieves all available services."
    )
    public ResponseEntity<List<ServiceResource>> getAllServices() {

        var query = new GetAllServicesQuery();

        var services = serviceQueryService.handle(query);

        var resources = services.stream()
                .map(ServiceResourceFromEntityAssembler::toResourceFromEntity)
                .collect(Collectors.toList());

        return ResponseEntity.ok(resources);
    }

    /**
     * Retrieves a service by id.
     *
     * @param serviceId service identifier
     * @return service resource
     */
    @GetMapping("/{serviceId}")
    @Operation(
            summary = "Get service by id",
            description = "Retrieves a service by its identifier."
    )
    public ResponseEntity<ServiceResource> getServiceById(
            @PathVariable Long serviceId
    ) {

        var query = new GetServiceByIdQuery(serviceId);

        var service = serviceQueryService.handle(query);

        if (service.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        var resource =
                ServiceResourceFromEntityAssembler
                        .toResourceFromEntity(service.get());

        return ResponseEntity.ok(resource);
    }

    /**
     * Updates a service.
     *
     * @param serviceId service identifier
     * @param resource update resource
     * @return updated service resource
     */
    @PutMapping("/{serviceId}")
    @Operation(
            summary = "Update service",
            description = "Updates an existing service."
    )
    public ResponseEntity<ServiceResource> updateService(
            @PathVariable Long serviceId,
            @RequestBody UpdateServiceResource resource
    ) {

        var command =
                UpdateServiceCommandFromResourceAssembler
                        .toCommandFromResource(serviceId, resource);

        var updatedService = serviceCommandService.handle(command);

        if (updatedService.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }

        var serviceResource =
                ServiceResourceFromEntityAssembler
                        .toResourceFromEntity(updatedService.get());

        return ResponseEntity.ok(serviceResource);
    }

    /**
     * Deactivates a service.
     *
     * @param serviceId service identifier
     * @return updated service resource
     */
    @PatchMapping("/{serviceId}/deactivate")
    @Operation(
            summary = "Deactivate service",
            description = "Deactivates an existing service."
    )
    public ResponseEntity<ServiceResource> deactivateService(
            @PathVariable Long serviceId
    ) {

        var command = new DeactivateServiceCommand(serviceId);

        var service = serviceCommandService.handle(command);

        if (service.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }

        var resource =
                ServiceResourceFromEntityAssembler
                        .toResourceFromEntity(service.get());

        return ResponseEntity.ok(resource);
    }

    /**
     * Adds a tag to a service.
     *
     * @param serviceId service identifier
     * @param resource tag resource
     * @return updated service resource
     */
    @PostMapping("/{serviceId}/tags")
    @Operation(
            summary = "Add tag to service",
            description = "Associates a tag with a service."
    )
    public ResponseEntity<ServiceResource> addTagToService(
            @PathVariable Long serviceId,
            @RequestBody AddTagToServiceResource resource
    ) {

        var command = new AddTagToServiceCommand(
                serviceId,
                resource.tagId()
        );

        var service = serviceCommandService.handle(command);

        if (service.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }

        var serviceResource =
                ServiceResourceFromEntityAssembler
                        .toResourceFromEntity(service.get());

        return ResponseEntity.ok(serviceResource);
    }
}