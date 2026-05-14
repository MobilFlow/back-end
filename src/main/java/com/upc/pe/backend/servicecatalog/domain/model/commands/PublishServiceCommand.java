package com.upc.pe.backend.servicecatalog.domain.model.commands;

/**
 * PublishServiceCommand
 *
 * Command used to publish
 * a new service into the platform.
 *
 * @param mechanicProfileId the mechanic profile identifier
 * @param title the service title
 * @param description the service description
 * @param priceMin minimum service price
 * @param priceMax maximum service price
 * @param categoryId service category id
 */
public record PublishServiceCommand(
        Long mechanicProfileId,
        String title,
        String description,
        Double priceMin,
        Double priceMax,
        Long categoryId
) {

    public PublishServiceCommand {

        if (mechanicProfileId == null)
            throw new IllegalArgumentException("mechanicProfileId cannot be null");

        if (title == null || title.isBlank())
            throw new IllegalArgumentException("title cannot be blank");

        if (description == null || description.isBlank())
            throw new IllegalArgumentException("description cannot be blank");

        if (categoryId == null)
            throw new IllegalArgumentException("categoryId cannot be null");
    }
}