package com.upc.pe.backend.servicecatalog.domain.model.queries;

/**
 * GetServicesByTagQuery
 *
 * Query used to retrieve
 * services associated with
 * a specific tag.
 *
 * @param tagId the tag identifier
 */
public record GetServicesByTagQuery(
        Long tagId
) {

    public GetServicesByTagQuery {

        if (tagId == null)
            throw new IllegalArgumentException("tagId cannot be null");
    }
}