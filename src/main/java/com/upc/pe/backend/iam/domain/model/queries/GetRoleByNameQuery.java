package com.upc.pe.backend.iam.domain.model.queries;

import com.upc.pe.backend.iam.domain.model.valueobjects.Roles;

/**
 * Query to retrieve a specific role by its name.
 *
 * @param roleName The name of the role to search for (e.g., Roles.ROLE_USER, Roles.ROLE_ADMIN).
 */
public record GetRoleByNameQuery(Roles roleName) {
    public GetRoleByNameQuery {
        if (roleName == null) {
            throw new IllegalArgumentException("Role name cannot be null");
        }
    }
}