package com.upc.pe.backend.servicecatalog.domain.services;

import com.upc.pe.backend.servicecatalog.domain.model.commands.CreateCategoryCommand;
import com.upc.pe.backend.servicecatalog.domain.model.entities.Category;

import java.util.Optional;

/**
 * CategoryCommandService interface
 *
 * This service defines command
 * operations related to categories
 * inside the ServiceCatalog bounded context.
 */
public interface CategoryCommandService {

    Optional<Category> handle(CreateCategoryCommand command);
}