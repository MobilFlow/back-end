package com.upc.pe.backend.iam.interfaces.rest.transform;

import com.upc.pe.backend.iam.domain.model.aggregates.User;
import com.upc.pe.backend.iam.interfaces.rest.resources.AuthenticatedUserResource;

/**
 * Utility class for transforming a {@link User} entity and authentication token
 * into an {@link AuthenticatedUserResource} representation.
 */
public class AuthenticatedUserResourceFromEntityAssembler {

    /**
     * Converts a User entity and token into an AuthenticatedUserResource.
     *
     * @param user the user entity to transform
     * @param token the JWT or access token assigned to the user
     * @return the resource representing the authenticated user
     */

    public static AuthenticatedUserResource toResourceFromEntity(User user, String token) {
        return new AuthenticatedUserResource(user.getId(), user.getEmail(), token);
    }
}