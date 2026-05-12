package com.upc.pe.backend.servicemanagement.interfaces.rest.transform;

import com.upc.pe.backend.servicemanagement.domain.model.commands.ConfirmServiceCompletionCommand;
import com.upc.pe.backend.servicemanagement.interfaces.rest.resources.ConfirmServiceCompletionResource;

public class ConfirmServiceCompletionCommandFromResourceAssembler {

    public static ConfirmServiceCompletionCommand toCommandFromResource(
            Long serviceId,
            ConfirmServiceCompletionResource resource
    ) {
        return new ConfirmServiceCompletionCommand(
                serviceId,
                resource.actorProfileId(),
                resource.role()
        );
    }
}
