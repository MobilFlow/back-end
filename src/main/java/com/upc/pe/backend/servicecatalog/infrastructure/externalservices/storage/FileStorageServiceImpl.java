package com.upc.pe.backend.servicecatalog.infrastructure.externalservices.storage;

import com.upc.pe.backend.servicecatalog.application.internal.outboundservices.storage.FileStorageService;
import org.springframework.stereotype.Service;

/**
 * FileStorageServiceImpl class
 *
 * External service implementation
 * responsible for handling
 * file and image storage.
 *
 * This service may connect to:
 * - AWS S3
 * - Firebase Storage
 * - Cloudinary
 * - Local file systems
 */
@Service
public class FileStorageServiceImpl implements FileStorageService {

    /**
     * Uploads a file.
     *
     * @param fileName file name
     * @param content file content
     * @return public file URL
     */
    @Override
    public String uploadFile(String fileName, byte[] content) {

        // Future cloud storage integration

        return "https://temporary-storage-url.com/" + fileName;
    }

    /**
     * Deletes a file.
     *
     * @param fileUrl file public URL
     */
    @Override
    public void deleteFile(String fileUrl) {

        // Future delete logic
    }
}