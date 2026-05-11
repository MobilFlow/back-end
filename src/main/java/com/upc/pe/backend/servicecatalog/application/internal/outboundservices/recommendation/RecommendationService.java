package com.upc.pe.backend.servicecatalog.application.internal.outboundservices.recommendation;

import java.util.List;

/**
 * RecommendationService interface
 *
 * This interface is used to communicate with
 * recommendation systems or AI services.
 *
 * It allows the ServiceCatalog bounded context
 * to retrieve personalized service recommendations
 * based on user preferences, searches or behavior.
 */
public interface RecommendationService {

    /**
     * Retrieves recommended services for a user.
     *
     * @param userId the user identifier
     * @return list of recommended service ids
     */
    List<Long> getRecommendedServices(Long userId);

    /**
     * Refreshes recommendation data for a service.
     *
     * @param serviceId the service identifier
     */
    void refreshServiceRecommendations(Long serviceId);
}