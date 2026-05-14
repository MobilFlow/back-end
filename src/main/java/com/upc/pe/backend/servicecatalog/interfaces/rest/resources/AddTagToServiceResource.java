package com.upc.pe.backend.servicecatalog.interfaces.rest.resources;

/**
 * AddTagToServiceResource
 *
 * Resource used to associate
 * a tag with a service.
 *
 * @param tagId tag identifier
 */
public record AddTagToServiceResource(
        Long tagId
) {
}