package com.upc.pe.backend.servicecatalog.interfaces.rest.resources;

/**
 * TagResource
 *
 * Resource representing
 * a service tag.
 */
public record TagResource(
        Long id,
        String name
) {
}