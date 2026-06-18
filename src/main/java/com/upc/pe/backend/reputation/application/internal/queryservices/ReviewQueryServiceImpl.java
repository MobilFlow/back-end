package com.upc.pe.backend.reputation.application.internal.queryservices;

import com.upc.pe.backend.reputation.domain.model.aggregates.Review;
import com.upc.pe.backend.reputation.domain.model.queries.GetAllReviewsQuery;
import com.upc.pe.backend.reputation.domain.model.queries.GetReviewByIdQuery;
import com.upc.pe.backend.reputation.domain.model.queries.GetReviewsByMechanicIdQuery;
import com.upc.pe.backend.reputation.domain.services.ReviewQueryService;
import com.upc.pe.backend.reputation.infrastructure.persistence.jpa.repositories.ReviewRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ReviewQueryServiceImpl implements ReviewQueryService {

    private final ReviewRepository reviewRepository;

    public ReviewQueryServiceImpl(ReviewRepository reviewRepository) {
        this.reviewRepository = reviewRepository;
    }

    @Override
    public List<Review> handle(GetAllReviewsQuery query) {
        return reviewRepository.findAll();
    }

    @Override
    public Optional<Review> handle(GetReviewByIdQuery query) {
        return reviewRepository.findById(query.reviewId());
    }

    @Override
    public List<Review> handle(GetReviewsByMechanicIdQuery query) {
        return reviewRepository.findAllByMechanicId(query.mechanicId());
    }
}
