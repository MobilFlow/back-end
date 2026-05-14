package com.upc.pe.backend.servicecatalog.interfaces.rest.transform;

import com.upc.pe.backend.servicecatalog.domain.model.commands.CreateCategoryCommand;
import com.upc.pe.backend.servicecatalog.interfaces.rest.resources.CategoryResource;

/**
 * CreateCategoryCommandFromResourceAssembler
 *
 * Converts CategoryResource
 * into CreateCategoryCommand.
 */
public class CreateCategoryCommandFromResourceAssembler {

    public static CreateCategoryCommand toCommandFromResource(
            CategoryResource resource
    ) {

        return new CreateCategoryCommand(
                resource.name()
        );
    }
}