package com.upc.pe.backend.servicecatalog.domain.model.queries;

/**
 * GetTagByIdQuery
 *
 * Query used to retrieve
 * a tag by its identifier.
 *
 * @param tagId the tag identifier
 */
public record GetTagByIdQuery(
        Long tagId
) {

    public GetTagByIdQuery {

        if (tagId == null)
            throw new IllegalArgumentException("tagId cannot be null");
    }
}