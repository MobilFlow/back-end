package com.upc.pe.backend.servicecatalog.application.internal.eventhandlers;

import com.upc.pe.backend.servicecatalog.domain.model.events.ServiceUpdatedEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;

/**
 * ServiceUpdatedEventHandler class
 *
 * This class is used to handle the ServiceUpdatedEvent
 * after a mechanic updates an existing service.
 *
 * The handler can be used to synchronize:
 * - Search indexes
 * - Cache entries
 * - Recommendation systems
 * - Analytics and audit logs
 *
 * This event belongs to the ServiceCatalog bounded context.
 */
@Service
public class ServiceUpdatedEventHandler {

    private static final Logger LOGGER =
            LoggerFactory.getLogger(ServiceUpdatedEventHandler.class);

    /**
     * Handle the ServiceUpdatedEvent.
     *
     * This method is executed when a service
     * is successfully updated.
     *
     * @param event the event to handle
     */
    @EventListener
    public void on(ServiceUpdatedEvent event) {

        LOGGER.info(
                "Service {} updated successfully at {}",
                event.serviceId(),
                currentTimestamp()
        );

        // Additional logic:
        // - update search indexes
        // - refresh recommendations
        // - synchronize external systems
    }

    /**
     * Returns the current timestamp.
     *
     * @return current timestamp
     */
    private Timestamp currentTimestamp() {
        return new Timestamp(System.currentTimeMillis());
    }
}

