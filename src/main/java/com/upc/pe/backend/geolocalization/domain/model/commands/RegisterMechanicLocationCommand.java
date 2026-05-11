package com.upc.pe.backend.geolocalization.domain.model.commands;

public record RegisterMechanicLocationCommand(
        Long mechanicId,
        Double latitude,
        Double longitude,
        String addressText
) {
    public RegisterMechanicLocationCommand {
        if (mechanicId == null)
            throw new IllegalArgumentException("mechanicId cannot be null");
        if (latitude == null || longitude == null)
            throw new IllegalArgumentException("latitude and longitude cannot be null");
        if (latitude < -90 || latitude > 90)
            throw new IllegalArgumentException("latitude must be between -90 and 90");
        if (longitude < -180 || longitude > 180)
            throw new IllegalArgumentException("longitude must be between -180 and 180");
    }
}
