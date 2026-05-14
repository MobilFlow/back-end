package com.upc.pe.backend.servicecatalog.interfaces.rest.transform;

import com.upc.pe.backend.servicecatalog.domain.model.commands.CreateTagCommand;
import com.upc.pe.backend.servicecatalog.interfaces.rest.resources.TagResource;

/**
 * CreateTagCommandFromResourceAssembler
 *
 * Converts TagResource
 * into CreateTagCommand.
 */
public class CreateTagCommandFromResourceAssembler {

    public static CreateTagCommand toCommandFromResource(
            TagResource resource
    ) {

        return new CreateTagCommand(
                resource.name()
        );
    }
}