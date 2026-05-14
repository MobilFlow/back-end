package com.upc.pe.backend.servicecatalog.application.internal.queryservices;

import com.upc.pe.backend.servicecatalog.domain.services.SearchQueryService;
import com.upc.pe.backend.servicecatalog.infrastructure.persistence.jpa.repositories.ServiceRepository;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * SearchQueryServiceImpl class
 *
 * This service is responsible for handling
 * advanced search and filtering operations
 * inside the ServiceCatalog bounded context.
 *
 * It provides functionality for:
 * - Service filtering
 * - Keyword searching
 * - Recommendation support
 * - Advanced queries
 *
 * This class belongs to the Application Layer.
 */
@Service
public class SearchQueryServiceImpl implements SearchQueryService {

    private final ServiceRepository serviceRepository;

    public SearchQueryServiceImpl(ServiceRepository serviceRepository) {
        this.serviceRepository = serviceRepository;
    }

    /**
     * Searches services using a keyword.
     *
     * @param keyword the search keyword
     * @return list of matching services
     */
    @Override
    public List<com.upc.pe.backend.servicecatalog.domain.model.aggregates.Service>
    searchServices(String keyword) {

        return serviceRepository
                .findByTitleContainingIgnoreCaseOrDescriptionContainingIgnoreCase(
                        keyword,
                        keyword
                );
    }
}