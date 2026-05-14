package com.upc.pe.backend.servicecatalog.application.internal.eventhandlers;

import com.upc.pe.backend.servicecatalog.domain.model.events.ServicePublishedEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;

/**
 * ServicePublishedEventHandler class
 *
 * This class is used to handle the ServicePublishedEvent
 * after a mechanic publishes a service in the platform.
 *
 * The handler can be used to trigger additional
 * application processes such as:
 * - Search indexing
 * - Cache synchronization
 * - Recommendation updates
 * - Logging and auditing
 *
 * This event belongs to the ServiceCatalog bounded context.
 */
@Service
public class ServicePublishedEventHandler {

    private static final Logger LOGGER =
            LoggerFactory.getLogger(ServicePublishedEventHandler.class);

    /**
     * Handle the ServicePublishedEvent.
     *
     * This method is executed when a service
     * is successfully published.
     *
     * @param event the event to handle
     */
    @EventListener
    public void on(ServicePublishedEvent event) {

        LOGGER.info(
                "Service {} published successfully at {}",
                event.serviceId(),
                currentTimestamp()
        );

        // Additional logic:
        // - search indexing
        // - notifications
        // - recommendation engine updates
    }

    /**
     * Returns the current timestamp.
     *
     * @return current timestamp
     */
    private Timestamp currentTimestamp() { return new Timestamp(System.currentTimeMillis()); } }
