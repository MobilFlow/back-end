package com.upc.pe.backend.reputation.domain.services;

import com.upc.pe.backend.reputation.domain.model.aggregates.Review;
import com.upc.pe.backend.reputation.domain.model.queries.GetAllReviewsQuery;
import com.upc.pe.backend.reputation.domain.model.queries.GetReviewByIdQuery;
import com.upc.pe.backend.reputation.domain.model.queries.GetReviewsByMechanicIdQuery;

import java.util.List;
import java.util.Optional;

public interface ReviewQueryService {

    List<Review> handle(GetAllReviewsQuery query);

    Optional<Review> handle(GetReviewByIdQuery query);

    List<Review> handle(GetReviewsByMechanicIdQuery query);
}
