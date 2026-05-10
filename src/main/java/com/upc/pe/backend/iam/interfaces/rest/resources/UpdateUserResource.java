package com.upc.pe.backend.iam.interfaces.rest.resources;

/**
 * Resource representing the editable fields of a user.
 * Used for update requests.
 */
public record UpdateUserResource(
        String name,
        String email,
        String profilePicture
)
{}