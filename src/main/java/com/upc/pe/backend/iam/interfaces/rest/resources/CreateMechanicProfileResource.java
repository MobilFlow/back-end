package com.upc.pe.backend.iam.interfaces.rest.resources;

public record CreateMechanicProfileResource(
        Long userId,
        String description,
        String workshopName,
        String workshopAddress
) {
}