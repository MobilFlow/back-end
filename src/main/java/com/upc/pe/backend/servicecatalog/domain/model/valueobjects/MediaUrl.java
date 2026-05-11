package com.upc.pe.backend.servicecatalog.domain.model.valueobjects;

import jakarta.persistence.Embeddable;

/**
 * MediaUrl Value Object
 *
 * Represents the public URL
 * of a service image or media file.
 */
@Embeddable
public class MediaUrl {

    private String url;

    public MediaUrl() {}

    public MediaUrl(String url) {

        if (url == null || url.isBlank()) {
            throw new IllegalArgumentException("Media URL cannot be blank");
        }

        this.url = url;
    }

    public String getUrl() {
        return url;
    }
}