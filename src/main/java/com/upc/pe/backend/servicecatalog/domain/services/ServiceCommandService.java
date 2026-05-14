package com.upc.pe.backend.servicecatalog.domain.services;

import com.upc.pe.backend.servicecatalog.domain.model.aggregates.Service;
import com.upc.pe.backend.servicecatalog.domain.model.commands.*;
import com.upc.pe.backend.servicecatalog.domain.model.entities.ServiceImage;

import java.util.Optional;

/**
 * ServiceCommandService interface
 *
 * This service defines all command
 * operations related to services
 * inside the ServiceCatalog bounded context.
 *
 * It handles:
 * - Service publication
 * - Service updates
 * - Service deactivation
 * - Tag association
 * - Image management
 */
public interface ServiceCommandService {

    Optional<Service> handle(PublishServiceCommand command);

    Optional<Service> handle(UpdateServiceCommand command);

    Optional<Service> handle(DeactivateServiceCommand command);

    Optional<ServiceImage> handle(UploadServiceImageCommand command);

    Optional<Service> handle(AddTagToServiceCommand command);

    Optional<Service> handle(RemoveTagFromServiceCommand command);

    void handle(DeleteServiceImageCommand command);
}