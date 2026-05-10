package com.upc.pe.backend.iam.domain.services;

import com.upc.pe.backend.iam.domain.model.entities.Role;
import com.upc.pe.backend.iam.domain.model.queries.GetAllRolesQuery;
import com.upc.pe.backend.iam.domain.model.queries.GetRoleByNameQuery;

import java.util.List;
import java.util.Optional;

/**
 * Query service interface for role management.
 * Defines operations to retrieve information about roles.
 */
public interface RoleQueryService {
    /**
     * Handles the query to get all roles.
     * @param query The query to get all roles.
     * @return A list of all {@link Role} entities.
     */
    List<Role> handle(GetAllRolesQuery query);

    /**
     * Handles the query to get a role by its name.
     * @param query The query containing the role name.
     * @return An {@link Optional} containing the {@link Role} entity if found, or empty if not.
     */
    Optional<Role> handle(GetRoleByNameQuery query);
}
