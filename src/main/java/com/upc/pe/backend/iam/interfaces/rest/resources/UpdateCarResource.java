package com.upc.pe.backend.iam.interfaces.rest.resources;

public record UpdateCarResource(
        String brand,
        String model,
        Integer year,
        String plate,
        String fuelType
) {
}