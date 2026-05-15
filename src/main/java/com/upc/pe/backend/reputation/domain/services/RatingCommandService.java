package com.upc.pe.backend.reputation.domain.services;

import com.upc.pe.backend.reputation.domain.model.aggregates.Rating;
import com.upc.pe.backend.reputation.domain.model.commands.CreateRatingCommand;
import com.upc.pe.backend.reputation.domain.model.commands.UpdateRatingCommand;

import java.util.Optional;

public interface RatingCommandService {

    Optional<Rating> handle(CreateRatingCommand command);

    Optional<Rating> handle(UpdateRatingCommand command);
}
