package com.upc.pe.backend.servicecatalog.domain.model.queries;

/**
 * GetServicesByCategoryQuery
 *
 * Query used to retrieve
 * services associated with
 * a specific category.
 *
 * @param categoryId the category identifier
 */
public record GetServicesByCategoryQuery(
        Long categoryId
) {

    public GetServicesByCategoryQuery {

        if (categoryId == null)
            throw new IllegalArgumentException("categoryId cannot be null");
    }
}