package com.upc.pe.backend.reputation.domain.services;

import com.upc.pe.backend.reputation.domain.model.aggregates.Rating;
import com.upc.pe.backend.reputation.domain.model.queries.GetAllRatingsQuery;
import com.upc.pe.backend.reputation.domain.model.queries.GetRatingByIdQuery;
import com.upc.pe.backend.reputation.domain.model.queries.GetRatingsByMechanicIdQuery;

import java.util.List;
import java.util.Optional;

public interface RatingQueryService {

    List<Rating> handle(GetAllRatingsQuery query);

    Optional<Rating> handle(GetRatingByIdQuery query);

    List<Rating> handle(GetRatingsByMechanicIdQuery query);
}
