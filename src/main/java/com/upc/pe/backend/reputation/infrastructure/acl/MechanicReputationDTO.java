package com.upc.pe.backend.reputation.infrastructure.acl;

public record MechanicReputationDTO(
    Double averageScore,
    Integer ratingsCount
) {}
