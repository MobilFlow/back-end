package com.upc.pe.backend.geolocalization.interfaces.rest.resources;

public record RegisterMechanicLocationResource(
        Double latitude,
        Double longitude,
        String addressText
) {
}