package com.upc.pe.backend.reputation.application.internal.queryservices;

import com.upc.pe.backend.reputation.domain.model.aggregates.Rating;
import com.upc.pe.backend.reputation.domain.model.dtos.ReputationSummaryDto;
import com.upc.pe.backend.reputation.domain.model.queries.GetMechanicReputationSummaryQuery;
import com.upc.pe.backend.reputation.domain.services.ReputationQueryService;
import com.upc.pe.backend.reputation.infrastructure.persistence.jpa.repositories.RatingRepository;
import com.upc.pe.backend.reputation.infrastructure.persistence.jpa.repositories.ReviewRepository;
import org.springframework.stereotype.Service;

@Service
public class ReputationQueryServiceImpl implements ReputationQueryService {

    private final RatingRepository ratingRepository;
    private final ReviewRepository reviewRepository;

    public ReputationQueryServiceImpl(
            RatingRepository ratingRepository,
            ReviewRepository reviewRepository
    ) {
        this.ratingRepository = ratingRepository;
        this.reviewRepository = reviewRepository;
    }

    @Override
    public ReputationSummaryDto handle(GetMechanicReputationSummaryQuery query) {
        var ratings = ratingRepository.findAllByMechanicId(query.mechanicId());
        var totalRatings = ratings.size();
        var totalReviews = reviewRepository.countByMechanicId(query.mechanicId());

        var averageRating = ratings.stream()
                .mapToInt(Rating::getScore)
                .average()
                .orElse(0.0);

        return new ReputationSummaryDto(
                averageRating,
                totalRatings,
                Math.toIntExact(totalReviews)
        );
    }
}
