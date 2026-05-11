package com.upc.pe.backend.servicecatalog.domain.model.queries;

/**
 * GetCategoryByIdQuery
 *
 * Query used to retrieve
 * a category by its identifier.
 *
 * @param categoryId the category identifier
 */
public record GetCategoryByIdQuery(
        Long categoryId
) {

    public GetCategoryByIdQuery {

        if (categoryId == null)
            throw new IllegalArgumentException("categoryId cannot be null");
    }
}