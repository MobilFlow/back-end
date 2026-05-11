package com.upc.pe.backend.reputation.application.internal.commandservices;

import com.upc.pe.backend.iam.infrastructure.acl.IAMContextFacade;
import com.upc.pe.backend.reputation.domain.model.aggregates.MechanicReputation;
import com.upc.pe.backend.reputation.domain.model.aggregates.Rating;
import com.upc.pe.backend.reputation.domain.model.commands.CreateRatingCommand;
import com.upc.pe.backend.reputation.domain.model.commands.UpdateRatingCommand;
import com.upc.pe.backend.reputation.domain.services.RatingCommandService;
import com.upc.pe.backend.reputation.infrastructure.persistence.jpa.repositories.MechanicReputationRepository;
import com.upc.pe.backend.reputation.infrastructure.persistence.jpa.repositories.RatingRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class RatingCommandServiceImpl implements RatingCommandService {

    private final RatingRepository ratingRepository;
    private final MechanicReputationRepository mechanicReputationRepository;
    private final IAMContextFacade iamContextFacade;

    public RatingCommandServiceImpl(
            RatingRepository ratingRepository,
            MechanicReputationRepository mechanicReputationRepository,
            IAMContextFacade iamContextFacade
    ) {
        this.ratingRepository = ratingRepository;
        this.mechanicReputationRepository = mechanicReputationRepository;
        this.iamContextFacade = iamContextFacade;
    }

    @Override
    @Transactional
    public Optional<Rating> handle(CreateRatingCommand command) {

        validateRating(command.score());
        validateFinishedService(command.serviceFinished(), "Rating");
        validateMechanicExists(command.mechanicId());

        if (ratingRepository.existsByMechanicIdAndDriverIdAndServiceId(
                command.mechanicId(),
                command.driverId(),
                command.serviceId()
        )) {
            throw new IllegalArgumentException("Rating already exists for this service");
        }

        var rating = new Rating(command);

        var savedRating = ratingRepository.save(rating);

        updateMechanicReputation(savedRating.getMechanicId());

        return Optional.of(savedRating);
    }

    @Override
    @Transactional
    public Optional<Rating> handle(UpdateRatingCommand command) {

        validateRating(command.score());
        validateFinishedService(command.serviceFinished(), "Rating");

        var rating = ratingRepository.findById(command.ratingId());

        if (rating.isEmpty()) {
            return Optional.empty();
        }

        rating.get().updateScore(command.score());

        var updatedRating = ratingRepository.save(rating.get());

        updateMechanicReputation(updatedRating.getMechanicId());

        return Optional.of(updatedRating);
    }

    private void validateRating(Integer score) {
        if (score == null || score < 1 || score > 5) {
            throw new IllegalArgumentException("Rating score must be between 1 and 5");
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

    private void updateMechanicReputation(Long mechanicId) {

        var ratings = ratingRepository.findAllByMechanicId(mechanicId);

        var ratingsCount = ratings.size();

        var averageScore = ratings.stream()
                .mapToInt(Rating::getScore)
                .average()
                .orElse(0.0);

        var mechanicReputation = mechanicReputationRepository.findByMechanicId(mechanicId)
                .orElseGet(() -> new MechanicReputation(mechanicId));

        mechanicReputation.updateAverage(averageScore, ratingsCount);

        mechanicReputationRepository.save(mechanicReputation);
    }
}
