package com.upc.pe.backend.servicecatalog.domain.model.commands;

/**
 * UploadServiceImageCommand
 *
 * Command used to upload
 * an image for a service.
 *
 * @param serviceId service identifier
 * @param imageUrl image public URL
 */
public record UploadServiceImageCommand(
        Long serviceId,
        String imageUrl
) {

    public UploadServiceImageCommand {

        if (serviceId == null)
            throw new IllegalArgumentException("serviceId cannot be null");

        if (imageUrl == null || imageUrl.isBlank())
            throw new IllegalArgumentException("imageUrl cannot be blank");
    }
}