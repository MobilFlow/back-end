package com.upc.pe.backend.iam.interfaces.rest.resources;

public record RegisterCarResource(
        String brand,
        String model,
        Integer year,
        String plate,
        String fuelType
) {
}