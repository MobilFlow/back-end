package com.upc.pe.backend.servicecatalog.interfaces.rest.transform;

import com.upc.pe.backend.servicecatalog.domain.model.entities.Category;
import com.upc.pe.backend.servicecatalog.interfaces.rest.resources.CategoryResource;

/**
 * CategoryResourceFromEntityAssembler
 *
 * Converts Category entity
 * into CategoryResource.
 */
public class CategoryResourceFromEntityAssembler {

    public static CategoryResource toResourceFromEntity(Category entity) {

        return new CategoryResource(
                entity.getId(),
                entity.getName()
        );
    }
}