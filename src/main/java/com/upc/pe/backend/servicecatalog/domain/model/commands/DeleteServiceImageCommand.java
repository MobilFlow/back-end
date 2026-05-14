package com.upc.pe.backend.servicecatalog.domain.model.commands;

/**
 * DeleteServiceImageCommand
 *
 * Command used to delete
 * a service image.
 *
 * @param serviceImageId service image identifier
 */
public record DeleteServiceImageCommand(
        Long serviceImageId
) {

    public DeleteServiceImageCommand {

        if (serviceImageId == null)
            throw new IllegalArgumentException("serviceImageId cannot be null");
    }
}