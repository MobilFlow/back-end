package com.upc.pe.backend.servicecatalog.domain.model.events;

/**
 * ServiceDeactivatedEvent
 *
 * Event triggered when a service
 * is deactivated from the platform.
 *
 * This event can be used for:
 * - Removing services from search results
 * - Recommendation cleanup
 * - Analytics updates
 * - Audit registration
 *
 * @param serviceId the deactivated service identifier
 */
public record ServiceDeactivatedEvent(
        Long serviceId
) {
}