package com.upc.pe.backend.iam.domain.model.commands;

public record CreateMechanicProfileCommand(
        Long userId,
        String description,
        String workshopName,
        String workshopAddress
) {
    public CreateMechanicProfileCommand {
        if (userId == null)
            throw new IllegalArgumentException("userId cannot be null");
    }
}

