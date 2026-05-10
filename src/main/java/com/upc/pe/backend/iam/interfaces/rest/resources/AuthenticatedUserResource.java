package com.upc.pe.backend.iam.interfaces.rest.resources;
/**
 * Resource representing an authenticated user.
 * Contains the user's ID, email, and the JWT token.
 *
 * @param id the unique identifier of the authenticated user
 * @param email the email address of the authenticated user
 * @param token the JWT token issued to the authenticated user
 */
public record AuthenticatedUserResource(Long id, String email, String token) {
}