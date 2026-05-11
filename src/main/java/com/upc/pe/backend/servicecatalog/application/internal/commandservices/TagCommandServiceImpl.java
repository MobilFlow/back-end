package com.upc.pe.backend.servicecatalog.application.internal.commandservices;

import com.upc.pe.backend.servicecatalog.domain.model.commands.CreateTagCommand;
import com.upc.pe.backend.servicecatalog.domain.model.entities.Tag;
import com.upc.pe.backend.servicecatalog.domain.services.TagCommandService;
import com.upc.pe.backend.servicecatalog.infrastructure.persistence.jpa.repositories.TagRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TagCommandServiceImpl implements TagCommandService {

    private final TagRepository tagRepository;

    public TagCommandServiceImpl(TagRepository tagRepository) {
        this.tagRepository = tagRepository;
    }

    @Override
    public Optional<Tag> handle(CreateTagCommand command) {

        if (tagRepository.existsByName(command.name())) {
            throw new IllegalArgumentException(
                    String.format("Tag '%s' already exists", command.name())
            );
        }

        var tag = new Tag(command.name());

        return Optional.of(tagRepository.save(tag));
    }
}