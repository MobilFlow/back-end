package com.upc.pe.backend.reputation.interfaces.rest;

import com.upc.pe.backend.reputation.domain.services.RatingCommandService;
import com.upc.pe.backend.reputation.domain.services.ReviewCommandService;
import com.upc.pe.backend.reputation.interfaces.rest.resources.*;
import com.upc.pe.backend.reputation.interfaces.rest.transform.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping(value = "/api/v1/reputation", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "Reputation", description = "Available Reputation Endpoints")
public class ReputationController {

    private final RatingCommandService ratingCommandService;
    private final ReviewCommandService reviewCommandService;

    public ReputationController(
            RatingCommandService ratingCommandService,
            ReviewCommandService reviewCommandService
    ) {
        this.ratingCommandService = ratingCommandService;
        this.reviewCommandService = reviewCommandService;
    }

    @PostMapping("/ratings")
    @Operation(summary = "Create mechanic rating")
    public ResponseEntity<RatingResource> createRating(
            @RequestBody CreateRatingResource resource
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
    @Operation(summary = "Update mechanic rating")
    public ResponseEntity<RatingResource> updateRating(
            @PathVariable Long id,
            @RequestBody UpdateRatingResource resource
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
    @Operation(summary = "Create mechanic review")
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
    @Operation(summary = "Update mechanic review")
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
}
