package com.upc.pe.backend.reputation.domain.services;

import com.upc.pe.backend.reputation.domain.model.aggregates.Review;
import com.upc.pe.backend.reputation.domain.model.commands.CreateReviewCommand;
import com.upc.pe.backend.reputation.domain.model.commands.UpdateReviewCommand;

import java.util.Optional;

public interface ReviewCommandService {

    Optional<Review> handle(CreateReviewCommand command);

    Optional<Review> handle(UpdateReviewCommand command);
}
