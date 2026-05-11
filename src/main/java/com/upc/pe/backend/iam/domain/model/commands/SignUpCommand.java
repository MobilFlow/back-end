package com.upc.pe.backend.iam.domain.model.commands;

import com.upc.pe.backend.iam.domain.model.entities.Role;

/**
 * Sign-Up Command (SignUpCommand) for new users.

 * This command encapsulates the necessary data to register a new user in the system.
 * @param email The user's email address, which will serve as their unique identifier (username).
 * @param password The user's desired password (will be hashed by the service before saving).
 * @param fullName The full name of the user.
 * @param phoneNumber The URL or path to the user's profile picture.
 */
public record SignUpCommand(
        String email,
        String password,
        String fullName,
        String phoneNumber,
        String roleName
) {
    public SignUpCommand {
        if (email == null || email.isBlank())
            throw new IllegalArgumentException("Email cannot be blank");
        if (password == null || password.isBlank())
            throw new IllegalArgumentException("Password cannot be blank");
        if (fullName == null || fullName.isBlank())
            throw new IllegalArgumentException("Full name cannot be blank");
        if (phoneNumber == null || phoneNumber.isBlank())
            throw new IllegalArgumentException("Phone number cannot be blank");
        if (roleName == null || roleName.isEmpty())
            throw new IllegalArgumentException("One role must be provided");
    }


}