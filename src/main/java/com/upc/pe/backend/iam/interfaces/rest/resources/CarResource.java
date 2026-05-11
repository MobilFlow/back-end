package com.upc.pe.backend.iam.interfaces.rest.resources;


public record CarResource(
        Long id,
        Long ownerId,
        String brand,
        String model,
        Integer year,
        String plate,
        String fuelType
) {
}