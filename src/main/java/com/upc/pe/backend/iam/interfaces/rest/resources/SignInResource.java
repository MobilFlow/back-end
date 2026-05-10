package com.upc.pe.backend.iam.interfaces.rest.resources;
/**
 * Resource representing the data required for signing in a user.
 *
 * @param email the user's email address used for authentication
 * @param password the user's password
 */
public record SignInResource(String email, String password) {
}