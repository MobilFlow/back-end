package com.upc.pe.backend.servicecatalog.interfaces.rest.resources;

/**
 * UpdateServiceResource
 *
 * Resource used to update
 * an existing service.
 *
 * @param title service title
 * @param description service description
 * @param priceMin minimum price
 * @param priceMax maximum price
 * @param categoryId category identifier
 */
public record UpdateServiceResource(
        String title,
        String description,
        Double priceMin,
        Double priceMax,
        Long categoryId
) {
}