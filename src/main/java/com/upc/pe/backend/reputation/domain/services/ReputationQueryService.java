package com.upc.pe.backend.reputation.domain.services;

import com.upc.pe.backend.reputation.domain.model.dtos.ReputationSummaryDto;
import com.upc.pe.backend.reputation.domain.model.queries.GetMechanicReputationSummaryQuery;

public interface ReputationQueryService {

    ReputationSummaryDto handle(GetMechanicReputationSummaryQuery query);
}
