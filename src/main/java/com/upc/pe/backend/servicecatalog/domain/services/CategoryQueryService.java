package com.upc.pe.backend.servicecatalog.domain.services;

import com.upc.pe.backend.servicecatalog.domain.model.entities.Category;
import com.upc.pe.backend.servicecatalog.domain.model.queries.GetAllCategoriesQuery;
import com.upc.pe.backend.servicecatalog.domain.model.queries.GetCategoryByIdQuery;

import java.util.List;
import java.util.Optional;

/**
 * CategoryQueryService interface
 *
 * This service defines query
 * operations related to categories
 * inside the ServiceCatalog bounded context.
 */
public interface CategoryQueryService {

    List<Category> handle(GetAllCategoriesQuery query);

    Optional<Category> handle(GetCategoryByIdQuery query);
}