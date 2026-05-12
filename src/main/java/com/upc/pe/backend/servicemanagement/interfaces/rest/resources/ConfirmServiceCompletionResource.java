package com.upc.pe.backend.servicemanagement.interfaces.rest.resources;

public record ConfirmServiceCompletionResource(
        Long actorProfileId,
        String role
) {
}
