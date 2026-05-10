package com.upc.pe.backend.iam.interfaces.rest.transform;

import com.upc.pe.backend.iam.domain.model.commands.SignUpCommand;
import com.upc.pe.backend.iam.interfaces.rest.resources.SignUpResource;

/**
 * Utility class for transforming a {@link SignUpResource}
 * into a {@link SignUpCommand} used by the application layer.
 */
public class SignUpCommandFromResourceAssembler {
    /**
     * Converts a SignUpResource into a SignUpCommand.
     *
     * @param resource the resource containing sign-up data
     * @return the command with user registration information
     */
    public static SignUpCommand toCommandFromResource(SignUpResource resource) {
        return new SignUpCommand(resource.email(), resource.password(), resource.fullName(), resource.phoneNumber());
    }
}