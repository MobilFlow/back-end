package com.upc.pe.backend.servicecatalog.interfaces.rest;

import com.upc.pe.backend.servicecatalog.domain.services.ServiceCommandService;
import com.upc.pe.backend.servicecatalog.interfaces.rest.resources.ServiceImageResource;
import com.upc.pe.backend.servicecatalog.interfaces.rest.resources.UploadServiceImageResource;
import com.upc.pe.backend.servicecatalog.interfaces.rest.transform.ServiceImageResourceFromEntityAssembler;
import com.upc.pe.backend.servicecatalog.interfaces.rest.transform.UploadServiceImageCommandFromResourceAssembler;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * ServiceImageController
 *
 * REST controller responsible
 * for service image endpoints.
 */
@RestController
@RequestMapping("/api/v1/service-images")
@Tag(name = "Service Images")
public class ServiceImageController {

    private final ServiceCommandService serviceCommandService;

    public ServiceImageController(
            ServiceCommandService serviceCommandService
    ) {
        this.serviceCommandService = serviceCommandService;
    }

    @PostMapping("/{serviceId}")
    public ResponseEntity<ServiceImageResource> uploadImage(
            @PathVariable Long serviceId,
            @RequestBody UploadServiceImageResource resource
    ) {

        var command =
                UploadServiceImageCommandFromResourceAssembler
                        .toCommandFromResource(serviceId, resource);

        var image = serviceCommandService.handle(command);

        if (image.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }

        var imageResource =
                ServiceImageResourceFromEntityAssembler
                        .toResourceFromEntity(image.get());

        return new ResponseEntity<>(
                imageResource,
                HttpStatus.CREATED
        );
    }
}