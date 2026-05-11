package com.upc.pe.backend.iam.interfaces.rest.resources;

import java.util.List;
/**
 * Resource representing a user's public information.
 *
 * @param id the unique identifier of the user
 * @param email the email address of the user
 * @param fullName the full name of the user
 * @param profilePicture the URL or path to the user's profile picture
 * @param role the list of role names assigned to the user
 */
public record UserResource(Long id, String email, String fullName, String profilePicture, String role) {
}