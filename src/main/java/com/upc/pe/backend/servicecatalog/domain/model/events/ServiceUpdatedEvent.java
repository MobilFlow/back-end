package com.upc.pe.backend.servicecatalog.domain.model.events;

/**
 * ServiceUpdatedEvent
 *
 * Event triggered when a service
 * is updated inside the platform.
 *
 * This event can be used for:
 * - Cache synchronization
 * - Search updates
 * - Recommendation refresh
 * - Audit logging
 *
 * @param serviceId the updated service identifier
 */
public record ServiceUpdatedEvent(
        Long serviceId
) {
}