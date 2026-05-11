package com.upc.pe.backend.servicecatalog.application.internal.commandservices;

import com.upc.pe.backend.servicecatalog.domain.model.commands.CreateCategoryCommand;
import com.upc.pe.backend.servicecatalog.domain.model.entities.Category;
import com.upc.pe.backend.servicecatalog.domain.services.CategoryCommandService;
import com.upc.pe.backend.servicecatalog.infrastructure.persistence.jpa.repositories.CategoryRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CategoryCommandServiceImpl implements CategoryCommandService {

    private final CategoryRepository categoryRepository;

    public CategoryCommandServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }
    @Override
    public Optional<Category> handle(CreateCategoryCommand command) {

        if (categoryRepository.existsByName(command.name())) {
            throw new IllegalArgumentException(
                    String.format("Category '%s' already exists", command.name())
            );
        }

        var category = new Category(command.name());

        return Optional.of(categoryRepository.save(category));
    }
}