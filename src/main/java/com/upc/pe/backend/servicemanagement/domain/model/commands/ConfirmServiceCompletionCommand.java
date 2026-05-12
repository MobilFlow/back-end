package com.upc.pe.backend.servicemanagement.domain.model.commands;

public record ConfirmServiceCompletionCommand(
        Long serviceId,
        Long actorProfileId,
        String role
) {
}
