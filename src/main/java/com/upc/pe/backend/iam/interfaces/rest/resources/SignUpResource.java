package com.upc.pe.backend.iam.interfaces.rest.resources;


/**
 * Resource representing the data required for registering a new user.
 *
 * @param email the email address of the user
 * @param password the password chosen by the user
 * @param fullName the full name of the user
 * @param phoneNumber the URL or path to the user's profile picture
 */
public record SignUpResource(String email, String password, String fullName, String phoneNumber, String role) {
}