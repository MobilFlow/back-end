package com.upc.pe.backend.servicecatalog.interfaces.rest.transform;

import com.upc.pe.backend.servicecatalog.domain.model.commands.UploadServiceImageCommand;
import com.upc.pe.backend.servicecatalog.interfaces.rest.resources.UploadServiceImageResource;

/**
 * UploadServiceImageCommandFromResourceAssembler
 *
 * Converts UploadServiceImageResource
 * into UploadServiceImageCommand.
 */
public class UploadServiceImageCommandFromResourceAssembler {

    public static UploadServiceImageCommand toCommandFromResource(
            Long serviceId,
            UploadServiceImageResource resource
    ) {

        return new UploadServiceImageCommand(
                serviceId,
                resource.imageUrl()
        );
    }
}