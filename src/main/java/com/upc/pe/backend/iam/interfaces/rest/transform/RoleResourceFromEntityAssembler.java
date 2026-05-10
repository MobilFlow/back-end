package com.upc.pe.backend.iam.interfaces.rest.transform;

import com.upc.pe.backend.iam.domain.model.entities.Role;
import com.upc.pe.backend.iam.interfaces.rest.resources.RoleResource;

/**
 * Utility class for transforming a {@link Role} entity
 * into a {@link RoleResource} representation.
 */
public class RoleResourceFromEntityAssembler {
    /**
     * Converts a Role entity into a RoleResource.
     *
     * @param role the role entity to transform
     * @return the resource representing the role
     */
    public static RoleResource toResourceFromEntity(Role role) {
        return new RoleResource(role.getId(), role.getStringName());
    }
}