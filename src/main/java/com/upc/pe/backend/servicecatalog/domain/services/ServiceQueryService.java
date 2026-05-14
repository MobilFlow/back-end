package com.upc.pe.backend.servicecatalog.domain.services;

import com.upc.pe.backend.servicecatalog.domain.model.aggregates.Service;
import com.upc.pe.backend.servicecatalog.domain.model.queries.*;

import java.util.List;
import java.util.Optional;

/**
 * ServiceQueryService interface
 *
 * This service defines all query
 * operations related to services
 * inside the ServiceCatalog bounded context.
 *
 * It handles:
 * - Service retrieval
 * - Search operations
 * - Filtering operations
 */
public interface ServiceQueryService {

    List<Service> handle(GetAllServicesQuery query);

    Optional<Service> handle(GetServiceByIdQuery query);

    List<Service> handle(GetServicesByCategoryQuery query);

    List<Service> handle(GetServicesByTagQuery query);

    List<Service> handle(SearchServicesQuery query);
}