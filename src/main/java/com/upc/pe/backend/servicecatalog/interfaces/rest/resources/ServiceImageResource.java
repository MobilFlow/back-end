package com.upc.pe.backend.servicecatalog.interfaces.rest.resources;

/**
 * ServiceImageResource
 *
 * Resource representing
 * a service image.
 */
public record ServiceImageResource(
        Long id,
        String imageUrl
) {
}