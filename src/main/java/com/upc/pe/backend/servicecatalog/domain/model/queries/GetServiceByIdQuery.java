package com.upc.pe.backend.servicecatalog.domain.model.queries;

/**
 * GetServiceByIdQuery
 *
 * Query used to retrieve
 * a service by its identifier.
 *
 * @param serviceId the service identifier
 */
public record GetServiceByIdQuery(
        Long serviceId
) {

    public GetServiceByIdQuery {

        if (serviceId == null)
            throw new IllegalArgumentException("serviceId cannot be null");
    }
}