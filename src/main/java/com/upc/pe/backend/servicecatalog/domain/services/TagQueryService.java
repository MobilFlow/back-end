package com.upc.pe.backend.servicecatalog.domain.services;

import com.upc.pe.backend.servicecatalog.domain.model.entities.Tag;
import com.upc.pe.backend.servicecatalog.domain.model.queries.GetAllTagsQuery;
import com.upc.pe.backend.servicecatalog.domain.model.queries.GetTagByIdQuery;

import java.util.List;
import java.util.Optional;

/**
 * TagQueryService interface
 *
 * This service defines query
 * operations related to tags
 * inside the ServiceCatalog bounded context.
 */
public interface TagQueryService {

    List<Tag> handle(GetAllTagsQuery query);

    Optional<Tag> handle(GetTagByIdQuery query);
}