package com.upc.pe.backend.iam.domain.model.commands;

/**
 * Sign-Up Command (SignUpCommand) for new users.

 * This command encapsulates the necessary data to register a new user in the system.
 * @param email The user's email address, which will serve as their unique identifier (username).
 * @param password The user's desired password (will be hashed by the service before saving).
 * @param fullName The full name of the user.
 * @param phoneNumber The URL or path to the user's profile picture.
 */
public record SignUpCommand(String email,
                            String password,
                            String fullName,
                            String phoneNumber) {

    /**
     * Validates the command inputs to ensure all required fields are present.
     *
     * @throws IllegalArgumentException if any required field (email, password, fullName, phoneNumber) is null or blank.
     */
    public SignUpCommand {
        if (email == null || email.isBlank())
            throw new IllegalArgumentException("Email cannot be null or blank");
        if (password == null || password.isBlank())
            throw new IllegalArgumentException("Password cannot be null or blank");
        if (fullName == null || fullName.isBlank())
            throw new IllegalArgumentException("fullName cannot be null or blank");
        if (phoneNumber == null || phoneNumber.isBlank())
            throw new IllegalArgumentException("Profile picture cannot be null or blank");
    }
}