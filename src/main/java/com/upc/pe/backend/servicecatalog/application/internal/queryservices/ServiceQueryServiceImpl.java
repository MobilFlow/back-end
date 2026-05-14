package com.upc.pe.backend.servicecatalog.application.internal.queryservices;

import com.upc.pe.backend.servicecatalog.domain.model.queries.*;
import com.upc.pe.backend.servicecatalog.domain.services.ServiceQueryService;
import com.upc.pe.backend.servicecatalog.infrastructure.persistence.jpa.repositories.ServiceRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * ServiceQueryServiceImpl class
 *
 * This service is responsible for handling
 * service-related queries inside the
 * ServiceCatalog bounded context.
 *
 * It provides functionality for:
 * - Retrieving services
 * - Searching services
 * - Filtering services
 * - Listing services by category or tag
 *
 * This class belongs to the Application Layer.
 */
@Service
public class ServiceQueryServiceImpl implements ServiceQueryService {

    private final ServiceRepository serviceRepository;

    public ServiceQueryServiceImpl(ServiceRepository serviceRepository) {
        this.serviceRepository = serviceRepository;
    }

    /**
     * Retrieves all services.
     *
     * @param query the query object
     * @return list of services
     */
    @Override
    public List<com.upc.pe.backend.servicecatalog.domain.model.aggregates.Service>
    handle(GetAllServicesQuery query) {

        return serviceRepository.findAll();
    }

    /**
     * Retrieves a service by id.
     *
     * @param query the query object
     * @return optional service
     */
    @Override
    public Optional<com.upc.pe.backend.servicecatalog.domain.model.aggregates.Service>
    handle(GetServiceByIdQuery query) {

        return serviceRepository.findById(query.serviceId());
    }

    /**
     * Retrieves services by category.
     *
     * @param query the query object
     * @return list of services
     */
    @Override
    public List<com.upc.pe.backend.servicecatalog.domain.model.aggregates.Service>
    handle(GetServicesByCategoryQuery query) {

        return serviceRepository.findByCategoryId(query.categoryId());
    }

    /**
     * Retrieves services by tag.
     *
     * @param query the query object
     * @return list of services
     */
    @Override
    public List<com.upc.pe.backend.servicecatalog.domain.model.aggregates.Service>
    handle(GetServicesByTagQuery query) {

        return serviceRepository.findByTags_Id(query.tagId());
    }

    /**
     * Searches services by keyword.
     *
     * @param query the query object
     * @return list of matching services
     */
    @Override
    public List<com.upc.pe.backend.servicecatalog.domain.model.aggregates.Service>
    handle(SearchServicesQuery query) {

        return serviceRepository
                .findByTitleContainingIgnoreCaseOrDescriptionContainingIgnoreCase(
                        query.keyword(),
                        query.keyword()
                );
    }
}