package com.upc.pe.backend.reputation.application.internal.commandservices;

import com.upc.pe.backend.iam.infrastructure.acl.IAMContextFacade;
import com.upc.pe.backend.reputation.domain.model.aggregates.Review;
import com.upc.pe.backend.reputation.domain.model.commands.CreateReviewCommand;
import com.upc.pe.backend.reputation.domain.model.commands.UpdateReviewCommand;
import com.upc.pe.backend.reputation.domain.services.ReviewCommandService;
import com.upc.pe.backend.reputation.infrastructure.persistence.jpa.repositories.ReviewRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class ReviewCommandServiceImpl implements ReviewCommandService {

    private final ReviewRepository reviewRepository;
    private final IAMContextFacade iamContextFacade;

    public ReviewCommandServiceImpl(
            ReviewRepository reviewRepository,
            IAMContextFacade iamContextFacade
    ) {
        this.reviewRepository = reviewRepository;
        this.iamContextFacade = iamContextFacade;
    }

    @Override
    @Transactional
    public Optional<Review> handle(CreateReviewCommand command) {

        validateReview(command.content());
        validateFinishedService(command.serviceFinished(), "Review");
        validateMechanicExists(command.mechanicId());

        if (reviewRepository.existsByMechanicIdAndDriverIdAndServiceId(
                command.mechanicId(),
                command.driverId(),
                command.serviceId()
        )) {
            throw new IllegalArgumentException("Review already exists for this service");
        }

        var review = new Review(command);

        return Optional.of(reviewRepository.save(review));
    }

    @Override
    @Transactional
    public Optional<Review> handle(UpdateReviewCommand command) {

        validateReview(command.content());
        validateFinishedService(command.serviceFinished(), "Review");

        var review = reviewRepository.findById(command.reviewId());

        if (review.isEmpty()) {
            return Optional.empty();
        }

        review.get().updateContent(command.content());

        return Optional.of(reviewRepository.save(review.get()));
    }

    private void validateReview(String content) {
        if (content == null || content.isBlank()) {
            throw new IllegalArgumentException("Review content is required");
        }
        if (content.length() > 1000) {
            throw new IllegalArgumentException("Review content must not exceed 1000 characters");
        }
    }

    private void validateFinishedService(Boolean serviceFinished, String action) {
        if (!Boolean.TRUE.equals(serviceFinished)) {
            throw new IllegalArgumentException(action + " is allowed only after a finished service");
        }
    }

    private void validateMechanicExists(Long mechanicId) {
        if (!iamContextFacade.existsMechanicProfileById(mechanicId)) {
            throw new IllegalArgumentException(
                    String.format("MechanicProfile %d does not exist", mechanicId)
            );
        }
    }
}
