package com.upc.pe.backend.servicecatalog.domain.model.queries;

/**
 * SearchServicesQuery
 *
 * Query used to search services
 * using keywords.
 *
 * The keyword can be matched against:
 * - Service title
 * - Description
 * - Related metadata
 *
 * @param keyword the search keyword
 */
public record SearchServicesQuery(
        String keyword
) {

    public SearchServicesQuery {

        if (keyword == null || keyword.isBlank())
            throw new IllegalArgumentException("keyword cannot be blank");
    }
}