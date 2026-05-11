package com.upc.pe.backend.iam.interfaces.rest.resources;

public record CreateDriverProfileResource(
        Long userId,
        String licenseNumber
) {
}