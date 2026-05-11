package com.upc.pe.backend.iam.interfaces.rest.resources;

import java.util.List;

public record MechanicProfileResource(
        Long id,
        Long userId,
        String bio,
        String workshopName,
        String workshopAddress,
        List<SpecialtyResource> specialties
) {
}