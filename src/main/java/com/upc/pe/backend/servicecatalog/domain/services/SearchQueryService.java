package com.upc.pe.backend.servicecatalog.domain.services;

import com.upc.pe.backend.servicecatalog.domain.model.aggregates.Service;

import java.util.List;

/**
 * SearchQueryService interface
 *
 * This service defines advanced
 * searching and filtering operations
 * for services inside the platform.
 */
public interface SearchQueryService {

    List<Service> searchServices(String keyword);
}