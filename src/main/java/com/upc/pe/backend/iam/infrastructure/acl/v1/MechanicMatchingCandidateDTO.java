package com.upc.pe.backend.iam.infrastructure.acl.v1;

import java.util.List;

public record MechanicMatchingCandidateDTO(
    Long mechanicProfileId,
    String workshopName,
    List<String> specialties
) {}
