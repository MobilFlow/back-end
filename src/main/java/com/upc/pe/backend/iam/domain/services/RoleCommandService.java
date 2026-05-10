package com.upc.pe.backend.iam.domain.services;


import com.upc.pe.backend.iam.domain.model.commands.SeedRolesCommand;

/**
 * Command service interface for role management.
 * Defines operations to modify or initialize roles.
 */
public interface RoleCommandService {
    /**
     * Handles the command to initialize default roles in the system.
     * This is typically used once at application startup.
     * @param command The command to initialize roles.
     */
    void handle(SeedRolesCommand command);
}