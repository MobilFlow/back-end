package com.upc.pe.backend.reputation.application.internal.queryservices;

import com.upc.pe.backend.reputation.domain.model.aggregates.Rating;
import com.upc.pe.backend.reputation.domain.model.queries.GetAllRatingsQuery;
import com.upc.pe.backend.reputation.domain.model.queries.GetRatingByIdQuery;
import com.upc.pe.backend.reputation.domain.services.RatingQueryService;
import com.upc.pe.backend.reputation.infrastructure.persistence.jpa.repositories.RatingRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RatingQueryServiceImpl implements RatingQueryService {

    private final RatingRepository ratingRepository;

    public RatingQueryServiceImpl(RatingRepository ratingRepository) {
        this.ratingRepository = ratingRepository;
    }

    @Override
    public List<Rating> handle(GetAllRatingsQuery query) {
        return ratingRepository.findAll();
    }

    @Override
    public Optional<Rating> handle(GetRatingByIdQuery query) {
        return ratingRepository.findById(query.ratingId());
    }
}
