package com.upc.pe.backend.iam.domain.model.commands;

/**
 * Command to initialize the system's default roles.
 * This command is used to ensure that essential roles like ROLE_USER and ROLE_ADMIN exist in the database.
 * It is typically executed once at application startup.
 */
public record SeedRolesCommand() {
}