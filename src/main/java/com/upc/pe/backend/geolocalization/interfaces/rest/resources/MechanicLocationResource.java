package com.upc.pe.backend.geolocalization.interfaces.rest.resources;

public record MechanicLocationResource(
        Long id,
        Long mechanicId,
        Double latitude,
        Double longitude,
        String addressText
) {
}