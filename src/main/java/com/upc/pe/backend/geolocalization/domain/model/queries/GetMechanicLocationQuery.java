package com.upc.pe.backend.geolocalization.domain.model.queries;

public record GetMechanicLocationQuery(Long mechanicId) {
    public GetMechanicLocationQuery {
        if (mechanicId == null)
            throw new IllegalArgumentException("mechanicId cannot be null");
    }
}
