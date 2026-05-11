package com.upc.pe.backend.iam.domain.model.queries;

public record GetMechanicProfileByUserIdQuery(Long userId) {
    public GetMechanicProfileByUserIdQuery { if (userId == null) throw new IllegalArgumentException("userId cannot be null"); }
}
