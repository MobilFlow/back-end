package com.upc.pe.backend.servicecatalog.interfaces.rest.transform;

import com.upc.pe.backend.servicecatalog.domain.model.entities.ServiceImage;
import com.upc.pe.backend.servicecatalog.interfaces.rest.resources.ServiceImageResource;

/**
 * ServiceImageResourceFromEntityAssembler
 *
 * Converts ServiceImage entity
 * into ServiceImageResource.
 */
public class ServiceImageResourceFromEntityAssembler {

    public static ServiceImageResource toResourceFromEntity(
            ServiceImage entity
    ) {

        return new ServiceImageResource(
                entity.getId(),
                entity.getImageUrl().getUrl()
        );
    }
}