package com.upc.pe.backend.reputation.interfaces.rest;

import com.upc.pe.backend.reputation.domain.model.queries.GetMechanicReputationSummaryQuery;
import com.upc.pe.backend.reputation.domain.model.queries.GetRatingsByMechanicIdQuery;
import com.upc.pe.backend.reputation.domain.model.queries.GetReviewsByMechanicIdQuery;
import com.upc.pe.backend.reputation.domain.services.RatingCommandService;
import com.upc.pe.backend.reputation.domain.services.RatingQueryService;
import com.upc.pe.backend.reputation.domain.services.ReputationQueryService;
import com.upc.pe.backend.reputation.domain.services.ReviewCommandService;
import com.upc.pe.backend.reputation.domain.services.ReviewQueryService;
import com.upc.pe.backend.reputation.interfaces.rest.resources.*;
import com.upc.pe.backend.reputation.interfaces.rest.transform.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping(value = "/api/v1/reputation", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "Reputation", description = "Available Reputation Endpoints")
public class ReputationController {

    private final RatingCommandService ratingCommandService;
    private final RatingQueryService ratingQueryService;
    private final ReviewCommandService reviewCommandService;
    private final ReviewQueryService reviewQueryService;
    private final ReputationQueryService reputationQueryService;

    public ReputationController(
            RatingCommandService ratingCommandService,
            RatingQueryService ratingQueryService,
            ReviewCommandService reviewCommandService,
            ReviewQueryService reviewQueryService,
            ReputationQueryService reputationQueryService
    ) {
        this.ratingCommandService = ratingCommandService;
        this.ratingQueryService = ratingQueryService;
        this.reviewCommandService = reviewCommandService;
        this.reviewQueryService = reviewQueryService;
        this.reputationQueryService = reputationQueryService;
    }

    @PostMapping("/ratings")
    @Operation(summary = "Create mechanic rating", tags = {"Reputation"})
    public ResponseEntity<RatingResource> createRating(
            @RequestBody @Valid CreateRatingResource resource
    ) {

        var command = CreateRatingCommandFromResourceAssembler
                .toCommandFromResource(resource);

        var rating = ratingCommandService.handle(command);

        if (rating.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }

        var response = RatingResourceFromEntityAssembler
                .toResourceFromEntity(rating.get());

        return ResponseEntity.ok(response);
    }

    @PutMapping("/ratings/{id}")
    @Operation(summary = "Update mechanic rating", tags = {"Reputation"})
    public ResponseEntity<RatingResource> updateRating(
            @PathVariable Long id,
            @RequestBody @Valid UpdateRatingResource resource
    ) {

        var command = UpdateRatingCommandFromResourceAssembler
                .toCommandFromResource(id, resource);

        var rating = ratingCommandService.handle(command);

        if (rating.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        var response = RatingResourceFromEntityAssembler
                .toResourceFromEntity(rating.get());

        return ResponseEntity.ok(response);
    }

    @PostMapping("/reviews")
    @Operation(summary = "Create mechanic review", tags = {"Reputation"})
    public ResponseEntity<ReviewResource> createReview(
            @RequestBody CreateReviewResource resource
    ) {

        var command = CreateReviewCommandFromResourceAssembler
                .toCommandFromResource(resource);

        var review = reviewCommandService.handle(command);

        if (review.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }

        var response = ReviewResourceFromEntityAssembler
                .toResourceFromEntity(review.get());

        return ResponseEntity.ok(response);
    }

    @PutMapping("/reviews/{id}")
    @Operation(summary = "Update mechanic review", tags = {"Reputation"})
    public ResponseEntity<ReviewResource> updateReview(
            @PathVariable Long id,
            @RequestBody UpdateReviewResource resource
    ) {

        var command = UpdateReviewCommandFromResourceAssembler
                .toCommandFromResource(id, resource);

        var review = reviewCommandService.handle(command);

        if (review.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        var response = ReviewResourceFromEntityAssembler
                .toResourceFromEntity(review.get());

        return ResponseEntity.ok(response);
    }

    @GetMapping("/mechanics/{mechanicId}/ratings")
    @Operation(summary = "Get ratings by mechanic ID", tags = {"Reputation"})
    public ResponseEntity<List<RatingResource>> getRatingsByMechanicId(
            @PathVariable Long mechanicId
    ) {
        var query = new GetRatingsByMechanicIdQuery(mechanicId);

        var ratings = ratingQueryService.handle(query);

        var resources = ratings.stream()
                .map(RatingResourceFromEntityAssembler::toResourceFromEntity)
                .collect(Collectors.toList());

        return ResponseEntity.ok(resources);
    }

    @GetMapping("/mechanics/{mechanicId}/reviews")
    @Operation(summary = "Get reviews by mechanic ID", tags = {"Reputation"})
    public ResponseEntity<List<ReviewResource>> getReviewsByMechanicId(
            @PathVariable Long mechanicId
    ) {
        var query = new GetReviewsByMechanicIdQuery(mechanicId);

        var reviews = reviewQueryService.handle(query);

        var resources = reviews.stream()
                .map(ReviewResourceFromEntityAssembler::toResourceFromEntity)
                .collect(Collectors.toList());

        return ResponseEntity.ok(resources);
    }

    @GetMapping("/mechanics/{mechanicId}/summary")
    @Operation(summary = "Get reputation summary by mechanic ID", tags = {"Reputation"})
    public ResponseEntity<ReputationSummaryResource> getSummaryByMechanicId(
            @PathVariable Long mechanicId
    ) {
        var query = new GetMechanicReputationSummaryQuery(mechanicId);

        var summary = reputationQueryService.handle(query);

        var response = ReputationSummaryResourceFromDtoAssembler.toResourceFromDto(summary);

        return ResponseEntity.ok(response);
    }
}
