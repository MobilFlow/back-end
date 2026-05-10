package com.upc.pe.backend.iam.interfaces.rest.resources;
/**
 * Resource representing a user role.
 * Contains the role's ID and its name.
 *
 * @param id the unique identifier of the role
 * @param name the name of the role (e.g., ROLE_USER, ROLE_ADMIN)
 */
public record RoleResource(Long id, String name) {
}