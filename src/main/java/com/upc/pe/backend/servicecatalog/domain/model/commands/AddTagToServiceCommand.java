package com.upc.pe.backend.servicecatalog.domain.model.commands;

/**
 * AddTagToServiceCommand
 *
 * Command used to associate
 * a tag with a service.
 *
 * @param serviceId service identifier
 * @param tagId tag identifier
 */
public record AddTagToServiceCommand(
        Long serviceId,
        Long tagId
) {

    public AddTagToServiceCommand {

        if (serviceId == null)
            throw new IllegalArgumentException("serviceId cannot be null");

        if (tagId == null)
            throw new IllegalArgumentException("tagId cannot be null");
    }
}