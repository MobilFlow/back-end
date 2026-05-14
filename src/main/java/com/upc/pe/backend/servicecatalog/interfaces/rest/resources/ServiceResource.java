package com.upc.pe.backend.servicecatalog.interfaces.rest.resources;

import java.util.List;

/**
 * ServiceResource
 *
 * Resource representing
 * a service returned
 * to API clients.
 */
public record ServiceResource(
        Long id,
        Long mechanicProfileId,
        String title,
        String description,
        Double minimumPrice,
        Double maximumPrice,
        String status,
        CategoryResource category,
        List<TagResource> tags,
        List<ServiceImageResource> images
) {
}