package com.upc.pe.backend.iam.domain.model.commands;

import com.upc.pe.backend.iam.domain.model.valueobjects.FuelType;

public record RegisterCarCommand(
        Long driverProfileId,
        String brand,
        String model,
        Integer year,
        String plate,
        FuelType fuelType
) {
    public RegisterCarCommand {
        if (driverProfileId == null)         throw new IllegalArgumentException("driverProfileId cannot be null");
        if (brand == null || brand.isBlank()) throw new IllegalArgumentException("brand cannot be blank");
        if (model == null || model.isBlank()) throw new IllegalArgumentException("model cannot be blank");
        if (year == null)            throw new IllegalArgumentException("year cannot be null");
        if (plate == null || plate.isBlank()) throw new IllegalArgumentException("plate cannot be blank");
        if (fuelType == null)        throw new IllegalArgumentException("fuelType cannot be null");
    }
}