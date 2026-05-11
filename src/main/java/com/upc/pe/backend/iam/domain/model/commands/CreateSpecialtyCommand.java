package com.upc.pe.backend.iam.domain.model.commands;

public record CreateSpecialtyCommand(String name) {
    public CreateSpecialtyCommand {
        if (name == null || name.isBlank())
            throw new IllegalArgumentException("Specialty name cannot be blank");
    }
}

