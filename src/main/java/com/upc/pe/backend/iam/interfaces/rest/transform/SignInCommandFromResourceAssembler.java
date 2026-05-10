package com.upc.pe.backend.iam.interfaces.rest.transform;

import com.upc.pe.backend.iam.domain.model.commands.SignInCommand;
import com.upc.pe.backend.iam.interfaces.rest.resources.SignInResource;

/**
 * Utility class for transforming a {@link SignInResource}
 * into a {@link SignInCommand} used by the application layer.
 */
public class SignInCommandFromResourceAssembler {
    /**
     * Converts a SignInResource into a SignInCommand.
     *
     * @param signInResource the resource containing the sign-in data
     * @return the command containing the credentials for authentication
     */
    public static SignInCommand toCommandFromResource(SignInResource signInResource) {
        return new SignInCommand(signInResource.email(), signInResource.password());
    }
}