package com.upc.pe.backend.servicecatalog.domain.model.commands;

/**
 * CreateCategoryCommand
 *
 * Command used to create
 * a new service category.
 *
 * @param name category name
 */
public record CreateCategoryCommand(
        String name
) {

    public CreateCategoryCommand {

        if (name == null || name.isBlank())
            throw new IllegalArgumentException("Category name cannot be blank");
    }
}