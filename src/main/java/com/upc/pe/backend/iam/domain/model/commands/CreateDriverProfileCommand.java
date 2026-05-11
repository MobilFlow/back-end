package com.upc.pe.backend.iam.domain.model.commands;

public record CreateDriverProfileCommand(
        Long userId
) {
    public CreateDriverProfileCommand {
        if (userId == null)
            throw new IllegalArgumentException("userId cannot be null");
    }
}
