package com.upc.pe.backend.iam.interfaces.rest.transform;

import com.upc.pe.backend.iam.domain.model.aggregates.User;
import com.upc.pe.backend.iam.domain.model.entities.Role;
import com.upc.pe.backend.iam.interfaces.rest.resources.UserResource;

import java.util.stream.Collectors;

/**
 * Utility class for transforming a {@link User} entity
 * into a {@link UserResource} for REST responses.
 */
public class UserResourceFromEntityAssembler {
    /**
     * Converts a User entity into a UserResource.
     *
     * @param user the domain User object
     * @return a REST resource representing the user
     */
    public static UserResource toResourceFromEntity(User user) {
        return new UserResource(user.getId(), user.getEmail(), user.getFullName(), user.getProfilePicture(), user.getRole().getStringName());
    }
}