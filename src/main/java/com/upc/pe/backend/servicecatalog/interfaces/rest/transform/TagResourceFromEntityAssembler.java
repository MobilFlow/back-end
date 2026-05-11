package com.upc.pe.backend.servicecatalog.interfaces.rest.transform;

import com.upc.pe.backend.servicecatalog.domain.model.entities.Tag;
import com.upc.pe.backend.servicecatalog.interfaces.rest.resources.TagResource;

/**
 * TagResourceFromEntityAssembler
 *
 * Converts Tag entity
 * into TagResource.
 */
public class TagResourceFromEntityAssembler {

    public static TagResource toResourceFromEntity(Tag entity) {

        return new TagResource(
                entity.getId(),
                entity.getName()
        );
    }
}