package com.upc.pe.backend.servicecatalog.application.internal.outboundservices.storage;

/**
 * FileStorageService interface
 *
 * This interface is used to manage external
 * file storage operations for the ServiceCatalog
 * bounded context.
 *
 * It is mainly used for handling:
 * - Service images
 * - Media uploads
 * - Media deletion
 *
 * Implementations may use:
 * - Cloudinary
 * - AWS S3
 * - Firebase Storage
 * - Local storage systems
 */
public interface FileStorageService {

    /**
     * Uploads a file to the storage provider.
     *
     * @param fileName the file name
     * @param content  the file content
     * @return the public URL of the uploaded file
     */
    String uploadFile(String fileName, byte[] content);

    /**
     * Deletes a file from the storage provider.
     *
     * @param fileUrl the file URL
     */
    void deleteFile(String fileUrl);
}