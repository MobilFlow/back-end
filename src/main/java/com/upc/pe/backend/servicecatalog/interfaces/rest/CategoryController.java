package com.upc.pe.backend.servicecatalog.interfaces.rest;

import com.upc.pe.backend.servicecatalog.domain.model.commands.CreateCategoryCommand;
import com.upc.pe.backend.servicecatalog.domain.model.queries.GetAllCategoriesQuery;
import com.upc.pe.backend.servicecatalog.domain.services.CategoryCommandService;
import com.upc.pe.backend.servicecatalog.domain.services.CategoryQueryService;
import com.upc.pe.backend.servicecatalog.interfaces.rest.resources.CategoryResource;
import com.upc.pe.backend.servicecatalog.interfaces.rest.transform.CategoryResourceFromEntityAssembler;
import com.upc.pe.backend.servicecatalog.interfaces.rest.transform.CreateCategoryCommandFromResourceAssembler;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

/**
 * CategoryController
 *
 * REST controller responsible
 * for category endpoints.
 */
@RestController
@RequestMapping("/api/v1/categories")
@Tag(name = "Categories")
public class CategoryController {

    private final CategoryCommandService categoryCommandService;
    private final CategoryQueryService categoryQueryService;

    public CategoryController(
            CategoryCommandService categoryCommandService,
            CategoryQueryService categoryQueryService
    ) {
        this.categoryCommandService = categoryCommandService;
        this.categoryQueryService = categoryQueryService;
    }

    @PostMapping
    public ResponseEntity<CategoryResource> createCategory(
            @RequestBody CategoryResource resource
    ) {

        var command =
                CreateCategoryCommandFromResourceAssembler
                        .toCommandFromResource(resource);

        var category = categoryCommandService.handle(command);

        if (category.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }

        var categoryResource =
                CategoryResourceFromEntityAssembler
                        .toResourceFromEntity(category.get());

        return new ResponseEntity<>(
                categoryResource,
                HttpStatus.CREATED
        );
    }

    @GetMapping
    public ResponseEntity<List<CategoryResource>> getAllCategories() {

        var query = new GetAllCategoriesQuery();

        var categories = categoryQueryService.handle(query);

        var resources = categories.stream()
                .map(CategoryResourceFromEntityAssembler::toResourceFromEntity)
                .collect(Collectors.toList());

        return ResponseEntity.ok(resources);
    }
}