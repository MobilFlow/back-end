package com.upc.pe.backend.servicecatalog.infrastructure.externalservices.recommendation;

import com.upc.pe.backend.servicecatalog.application.internal.outboundservices.recommendation.RecommendationService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * RecommendationServiceImpl class
 *
 * External service implementation
 * used to connect with recommendation
 * systems or AI engines.
 */
@Service
public class RecommendationServiceImpl implements RecommendationService {

    /**
     * Retrieves recommended services.
     *
     * @param userId user identifier
     * @return recommended service ids
     */
    @Override
    public List<Long> getRecommendedServices(Long userId) {

        // Future FAISS/OpenAI integration

        return List.of();
    }

    /**
     * Refreshes recommendation data.
     *
     * @param serviceId service identifier
     */
    @Override
    public void refreshServiceRecommendations(Long serviceId) {

        // Future recommendation sync logic
    }
}