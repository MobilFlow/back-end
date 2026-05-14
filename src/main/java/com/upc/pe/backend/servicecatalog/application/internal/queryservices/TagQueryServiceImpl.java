package com.upc.pe.backend.servicecatalog.application.internal.queryservices;

import com.upc.pe.backend.servicecatalog.domain.model.entities.Tag;
import com.upc.pe.backend.servicecatalog.domain.model.queries.GetAllTagsQuery;
import com.upc.pe.backend.servicecatalog.domain.model.queries.GetTagByIdQuery;
import com.upc.pe.backend.servicecatalog.domain.services.TagQueryService;
import com.upc.pe.backend.servicecatalog.infrastructure.persistence.jpa.repositories.TagRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * TagQueryServiceImpl class
 *
 * This service is responsible for handling
 * tag-related queries inside the
 * ServiceCatalog bounded context.
 *
 * It provides functionality for:
 * - Retrieving tags
 * - Searching tags
 *
 * This class belongs to the Application Layer.
 */
@Service
public class TagQueryServiceImpl implements TagQueryService {

    private final TagRepository tagRepository;

    public TagQueryServiceImpl(TagRepository tagRepository) {
        this.tagRepository = tagRepository;
    }

    /**
     * Retrieves all tags.
     *
     * @param query the query object
     * @return list of tags
     */
    @Override
    public List<Tag> handle(GetAllTagsQuery query) {
        return tagRepository.findAll();
    }

    /**
     * Retrieves a tag by id.
     *
     * @param query the query object
     * @return optional tag
     */
    @Override
    public Optional<Tag> handle(GetTagByIdQuery query) {
        return tagRepository.findById(query.tagId());
    }
}