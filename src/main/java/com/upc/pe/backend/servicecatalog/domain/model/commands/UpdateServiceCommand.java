package com.upc.pe.backend.servicecatalog.domain.model.commands;

/**
 * UpdateServiceCommand
 *
 * Command used to update
 * an existing service.
 *
 * @param serviceId the service identifier
 * @param title the service title
 * @param description the service description
 * @param priceMin minimum service price
 * @param priceMax maximum service price
 * @param categoryId category identifier
 */
public record UpdateServiceCommand(
        Long serviceId,
        String title,
        String description,
        Double priceMin,
        Double priceMax,
        Long categoryId
) {

    public UpdateServiceCommand {

        if (serviceId == null)
            throw new IllegalArgumentException("serviceId cannot be null");

        if (title == null || title.isBlank())
            throw new IllegalArgumentException("title cannot be blank");

        if (description == null || description.isBlank())
            throw new IllegalArgumentException("description cannot be blank");

        if (categoryId == null)
            throw new IllegalArgumentException("categoryId cannot be null");
    }
}