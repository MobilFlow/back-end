package com.upc.pe.backend.servicecatalog.domain.model.commands;

/**
 * DeactivateServiceCommand
 *
 * Command used to deactivate
 * a service from the platform.
 *
 * @param serviceId the service identifier
 */
public record DeactivateServiceCommand(
        Long serviceId
) {

    public DeactivateServiceCommand {

        if (serviceId == null)
            throw new IllegalArgumentException("serviceId cannot be null");
    }
}