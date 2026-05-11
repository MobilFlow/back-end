package com.upc.pe.backend.iam.domain.model.commands;

import com.upc.pe.backend.iam.domain.model.valueobjects.FuelType;

public record UpdateCarCommand(
        Long carId,
        String brand,
        String model,
        Integer year,
        String plate,
        FuelType fuelType
) {}