package com.upc.pe.backend.servicecatalog.interfaces.rest.transform;

import com.upc.pe.backend.servicecatalog.domain.model.aggregates.Service;
import com.upc.pe.backend.servicecatalog.interfaces.rest.resources.ServiceResource;

import java.util.stream.Collectors;

/**
 * ServiceResourceFromEntityAssembler
 *
 * Converts Service entity
 * into ServiceResource.
 */
public class ServiceResourceFromEntityAssembler {

    public static ServiceResource toResourceFromEntity(Service entity) {

        return new ServiceResource(
                entity.getId(),
                entity.getMechanicProfileId(),
                entity.getTitle(),
                entity.getDescription(),
                entity.getPriceRange().getMinimumPrice().doubleValue(),
                entity.getPriceRange().getMaximumPrice().doubleValue(),
                entity.getStatus().name(),
                CategoryResourceFromEntityAssembler
                        .toResourceFromEntity(entity.getCategory()),
                entity.getTags()
                        .stream()
                        .map(TagResourceFromEntityAssembler::toResourceFromEntity)
                        .collect(Collectors.toList()),
                entity.getImages()
                        .stream()
                        .map(ServiceImageResourceFromEntityAssembler::toResourceFromEntity)
                        .collect(Collectors.toList())
        );
    }
}