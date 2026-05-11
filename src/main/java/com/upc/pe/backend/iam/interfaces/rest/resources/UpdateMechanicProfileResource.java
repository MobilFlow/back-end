package com.upc.pe.backend.iam.interfaces.rest.resources;

public record UpdateMechanicProfileResource(
        String description,
        String workshopName,
        String workshopAddress
) {
}