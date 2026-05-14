package com.upc.pe.backend.servicecatalog.interfaces.rest.transform;

import com.upc.pe.backend.servicecatalog.domain.model.commands.PublishServiceCommand;
import com.upc.pe.backend.servicecatalog.interfaces.rest.resources.PublishServiceResource;

/**
 * PublishServiceCommandFromResourceAssembler
 *
 * This assembler transforms
 * PublishServiceResource objects
 * into PublishServiceCommand objects.
 *
 * It belongs to the Interface Layer.
 */
public class PublishServiceCommandFromResourceAssembler {

    /**
     * Converts a resource into command.
     *
     * @param resource incoming REST resource
     * @return publish service command
     */
    public static PublishServiceCommand
    toCommandFromResource(
            PublishServiceResource resource
    ) {

        return new PublishServiceCommand(
                resource.mechanicProfileId(),
                resource.title(),
                resource.description(),
                resource.priceMin(),
                resource.priceMax(),
                resource.categoryId()
        );
    }
}