package com.upc.pe.backend.iam.domain.model.queries;

public record GetDriverProfileByUserIdQuery(Long userId) {
    public GetDriverProfileByUserIdQuery { if (userId == null) throw new IllegalArgumentException("userId cannot be null"); }
}
