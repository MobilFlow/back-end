package com.upc.pe.backend.servicecatalog.domain.model.commands;

/**
 * CreateTagCommand
 *
 * Command used to create
 * a new service tag.
 *
 * @param name tag name
 */
public record CreateTagCommand(
        String name
) {

    public CreateTagCommand {

        if (name == null || name.isBlank())
            throw new IllegalArgumentException("Tag name cannot be blank");
    }
}