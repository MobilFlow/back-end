package com.upc.pe.backend.servicecatalog.domain.model.commands;

/**
 * RemoveTagFromServiceCommand
 *
 * Command used to remove
 * a tag from a service.
 *
 * @param serviceId service identifier
 * @param tagId tag identifier
 */
public record RemoveTagFromServiceCommand(
        Long serviceId,
        Long tagId
) {

    public RemoveTagFromServiceCommand {

        if (serviceId == null)
            throw new IllegalArgumentException("serviceId cannot be null");

        if (tagId == null)
            throw new IllegalArgumentException("tagId cannot be null");
    }
}