package com.upc.pe.backend.servicecatalog.domain.services;

import com.upc.pe.backend.servicecatalog.domain.model.commands.UploadServiceImageCommand;
import com.upc.pe.backend.servicecatalog.domain.model.entities.ServiceImage;

import java.util.Optional;

/**
 * ServiceImageCommandService interface
 *
 * This service defines command
 * operations related to
 * service images.
 */
public interface ServiceImageCommandService {

    /**
     * Uploads a service image.
     *
     * @param command upload image command
     * @return created image
     */
    Optional<ServiceImage> handle(
            UploadServiceImageCommand command
    );
}