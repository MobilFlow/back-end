package com.upc.pe.backend.reputation.interfaces.rest.transform;

import com.upc.pe.backend.reputation.domain.model.dtos.ReputationSummaryDto;
import com.upc.pe.backend.reputation.interfaces.rest.resources.ReputationSummaryResource;

public class ReputationSummaryResourceFromDtoAssembler {

    public static ReputationSummaryResource toResourceFromDto(ReputationSummaryDto dto) {
        return new ReputationSummaryResource(
                dto.averageRating(),
                dto.totalRatings(),
                dto.totalReviews()
        );
    }
}
