package com.upc.pe.backend.servicecatalog.domain.model.events;

/**
 * ServicePublishedEvent
 *
 * Event triggered when a mechanic
 * publishes a new service.
 *
 * This event can be used for:
 * - Search indexing
 * - Notifications
 * - Recommendation updates
 * - Analytics
 *
 * @param serviceId the published service identifier
 */
public record ServicePublishedEvent(
        Long serviceId
) {
}