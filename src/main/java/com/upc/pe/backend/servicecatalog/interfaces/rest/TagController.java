package com.upc.pe.backend.servicecatalog.interfaces.rest;

import com.upc.pe.backend.servicecatalog.domain.model.queries.GetAllTagsQuery;
import com.upc.pe.backend.servicecatalog.domain.services.TagCommandService;
import com.upc.pe.backend.servicecatalog.domain.services.TagQueryService;
import com.upc.pe.backend.servicecatalog.interfaces.rest.resources.TagResource;
import com.upc.pe.backend.servicecatalog.interfaces.rest.transform.CreateTagCommandFromResourceAssembler;
import com.upc.pe.backend.servicecatalog.interfaces.rest.transform.TagResourceFromEntityAssembler;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

/**
 * TagController
 *
 * REST controller responsible
 * for tag endpoints.
 */
@RestController
@RequestMapping("/api/v1/tags")
@Tag(name = "Tags")
public class TagController {

    private final TagCommandService tagCommandService;
    private final TagQueryService tagQueryService;

    public TagController(
            TagCommandService tagCommandService,
            TagQueryService tagQueryService
    ) {
        this.tagCommandService = tagCommandService;
        this.tagQueryService = tagQueryService;
    }

    @PostMapping
    public ResponseEntity<TagResource> createTag(
            @RequestBody TagResource resource
    ) {

        var command =
                CreateTagCommandFromResourceAssembler
                        .toCommandFromResource(resource);

        var tag = tagCommandService.handle(command);

        if (tag.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }

        var tagResource =
                TagResourceFromEntityAssembler
                        .toResourceFromEntity(tag.get());

        return new ResponseEntity<>(
                tagResource,
                HttpStatus.CREATED
        );
    }

    @GetMapping
    public ResponseEntity<List<TagResource>> getAllTags() {

        var query = new GetAllTagsQuery();

        var tags = tagQueryService.handle(query);

        var resources = tags.stream()
                .map(TagResourceFromEntityAssembler::toResourceFromEntity)
                .collect(Collectors.toList());

        return ResponseEntity.ok(resources);
    }
}