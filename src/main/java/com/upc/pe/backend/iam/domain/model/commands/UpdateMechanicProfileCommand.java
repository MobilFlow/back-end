package com.upc.pe.backend.iam.domain.model.commands;

public record UpdateMechanicProfileCommand(
        Long mechanicProfileId,
        String description,
        String workshopName,
        String workshopAddress
) {}