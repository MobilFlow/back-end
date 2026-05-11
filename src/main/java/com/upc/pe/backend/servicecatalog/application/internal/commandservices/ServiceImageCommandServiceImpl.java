package com.upc.pe.backend.servicecatalog.application.internal.commandservices;


import com.upc.pe.backend.servicecatalog.domain.model.commands.UploadServiceImageCommand;
import com.upc.pe.backend.servicecatalog.domain.model.entities.ServiceImage;
import com.upc.pe.backend.servicecatalog.domain.model.valueobjects.MediaUrl;
import com.upc.pe.backend.servicecatalog.domain.services.ServiceImageCommandService;
import com.upc.pe.backend.servicecatalog.infrastructure.persistence.jpa.repositories.ServiceImageRepository;
import com.upc.pe.backend.servicecatalog.infrastructure.persistence.jpa.repositories.ServiceRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * ServiceImageCommandServiceImpl
 *
 * This service is responsible for handling
 * commands related to service images.
 *
 * Responsibilities:
 * - Upload service images
 * - Associate images to services
 * - Persist image entities
 */
@Service
public class ServiceImageCommandServiceImpl
        implements ServiceImageCommandService {

    private final ServiceRepository serviceRepository;
    private final ServiceImageRepository serviceImageRepository;

    public ServiceImageCommandServiceImpl(
            ServiceRepository serviceRepository,
            ServiceImageRepository serviceImageRepository
    ) {
        this.serviceRepository = serviceRepository;
        this.serviceImageRepository = serviceImageRepository;
    }

    /**
     * Uploads and associates an image
     * to an existing service.
     *
     * @param command upload image command
     * @return created ServiceImage
     */
    @Override
    @Transactional
    public Optional<ServiceImage> handle(
            UploadServiceImageCommand command
    ) {

        var service = serviceRepository.findById(command.serviceId())
                .orElseThrow(() ->
                        new IllegalArgumentException(
                                String.format(
                                        "Service %d not found",
                                        command.serviceId()
                                )
                        )
                );

        var image = new ServiceImage(
                new MediaUrl(command.imageUrl())
        );

        service.addImage(image);

        serviceRepository.save(service);

        return Optional.of(image);
    }
}