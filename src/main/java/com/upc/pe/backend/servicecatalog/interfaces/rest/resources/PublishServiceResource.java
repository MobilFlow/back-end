package com.upc.pe.backend.servicecatalog.interfaces.rest.resources;

/**
 * PublishServiceResource
 *
 * Resource used to publish
 * a new mechanic service.
 *
 * @param mechanicProfileId mechanic profile identifier
 * @param title service title
 * @param description service description
 * @param priceMin minimum price
 * @param priceMax maximum price
 * @param categoryId category identifier
 */
public record PublishServiceResource(
        Long mechanicProfileId,
        String title,
        String description,
        Double priceMin,
        Double priceMax,
        Long categoryId
) {
}