
package com.upc.pe.backend.servicecatalog.application.internal.eventhandlers;
import com.upc.pe.backend.servicecatalog.domain.model.events.ServiceDeactivatedEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;

/**
 * ServiceDeactivatedEventHandler class
 *
 * This class is used to handle the ServiceDeactivatedEvent
 * after a mechanic deactivates a service.
 *
 * The handler can be used to:
 * - Remove services from search results
 * - Clear recommendation entries
 * - Synchronize cache systems
 * - Register audit information
 *
 * This event belongs to the ServiceCatalog bounded context.
 */
@Service
public class ServiceDeactivatedEventHandler {

    private static final Logger LOGGER =
 LoggerFactory.getLogger(ServiceDeactivatedEventHandler.class);

    /**
     * Handle the ServiceDeactivatedEvent.
     *
     * This method is executed when a service
     * is successfully deactivated.
     *
     * @param event the event to handle
     */
    @EventListener
    public void on(ServiceDeactivatedEvent event) {

        LOGGER.info(
                "Service {} deactivated successfully at {}",
                event.serviceId(),
                currentTimestamp()
        );

        // Additional logic:
        // - remove service from search engine
        // - update recommendations
        // - clear cache entries
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
