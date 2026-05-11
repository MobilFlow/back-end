package com.upc.pe.backend.iam.domain.model.commands;


public record UpdateDriverProfileCommand(
        Long driverProfileId,
        String licenseNumber,
        String licenseExpiryDate
) {}
