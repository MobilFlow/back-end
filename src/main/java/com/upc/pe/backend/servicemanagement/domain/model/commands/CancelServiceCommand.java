package com.upc.pe.backend.servicemanagement.domain.model.commands;

public record CancelServiceCommand(
        Long serviceId,
        Long actorProfileId,
        String role
) {
}
