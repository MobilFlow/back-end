package com.upc.pe.backend.servicecatalog.interfaces.rest.resources;

/**
 * UploadServiceImageResource
 *
 * Resource used to upload
 * a service image.
 *
 * @param imageUrl image public URL
 */
public record UploadServiceImageResource(
        String imageUrl
) {
}