package com.upc.pe.backend.servicecatalog.application.internal.outboundservices.search;

/**
 * SearchService interface
 *
 * This interface is used to interact with
 * external search engines or indexing systems.
 *
 * It allows the ServiceCatalog bounded context
 * to synchronize service information with
 * search providers for filtering and searching.
 */
public interface SearchService {

    /**
     * Index a service in the search engine.
     *
     * @param serviceId the service identifier
     */
    void indexService(Long serviceId);

    /**
     * Update a service index.
     *
     * @param serviceId the service identifier
     */
    void updateServiceIndex(Long serviceId);

    /**
     * Remove a service from the search engine.
     *
     * @param serviceId the service identifier
     */
    void removeServiceIndex(Long serviceId);
}