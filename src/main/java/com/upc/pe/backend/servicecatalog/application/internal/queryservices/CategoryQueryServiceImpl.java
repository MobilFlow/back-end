package com.upc.pe.backend.servicecatalog.application.internal.queryservices;

import com.upc.pe.backend.servicecatalog.domain.model.entities.Category;
import com.upc.pe.backend.servicecatalog.domain.model.queries.GetAllCategoriesQuery;
import com.upc.pe.backend.servicecatalog.domain.model.queries.GetCategoryByIdQuery;
import com.upc.pe.backend.servicecatalog.domain.services.CategoryQueryService;
import com.upc.pe.backend.servicecatalog.infrastructure.persistence.jpa.repositories.CategoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * CategoryQueryServiceImpl class
 *
 * This service is responsible for handling
 * category-related queries inside the
 * ServiceCatalog bounded context.
 *
 * It provides functionality for:
 * - Retrieving categories
 * - Searching categories
 *
 * This class belongs to the Application Layer.
 */
@Service
public class CategoryQueryServiceImpl implements CategoryQueryService {

    private final CategoryRepository categoryRepository;

    public CategoryQueryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    /**
     * Retrieves all categories.
     *
     * @param query the query object
     * @return list of categories
     */
    @Override
    public List<Category> handle(GetAllCategoriesQuery query) {
        return categoryRepository.findAll();
    }

    /**
     * Retrieves a category by id.
     *
     * @param query the query object
     * @return optional category
     */
    @Override
    public Optional<Category> handle(GetCategoryByIdQuery query) {
        return categoryRepository.findById(query.categoryId());
    }
}