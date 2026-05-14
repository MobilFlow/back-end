package com.upc.pe.backend.servicecatalog.domain.services;

import com.upc.pe.backend.servicecatalog.domain.model.commands.CreateTagCommand;
import com.upc.pe.backend.servicecatalog.domain.model.entities.Tag;

import java.util.Optional;

/**
 * TagCommandService interface
 *
 * This service defines command
 * operations related to tags
 * inside the ServiceCatalog bounded context.
 */
public interface TagCommandService {

    Optional<Tag> handle(CreateTagCommand command);
}