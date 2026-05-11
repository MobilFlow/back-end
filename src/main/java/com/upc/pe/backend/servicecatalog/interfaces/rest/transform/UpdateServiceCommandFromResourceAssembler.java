package com.upc.pe.backend.servicecatalog.interfaces.rest.transform;

import com.upc.pe.backend.servicecatalog.domain.model.commands.UpdateServiceCommand;
import com.upc.pe.backend.servicecatalog.interfaces.rest.resources.UpdateServiceResource;

/**
 * UpdateServiceCommandFromResourceAssembler
 *
 * Converts UpdateServiceResource
 * into UpdateServiceCommand.
 */
public class UpdateServiceCommandFromResourceAssembler {

    public static UpdateServiceCommand toCommandFromResource(
            Long serviceId,
            UpdateServiceResource resource
    ) {

        return new UpdateServiceCommand(
                serviceId,
                resource.title(),
                resource.description(),
                resource.priceMin(),
                resource.priceMax(),
                resource.categoryId()
        );
    }
}