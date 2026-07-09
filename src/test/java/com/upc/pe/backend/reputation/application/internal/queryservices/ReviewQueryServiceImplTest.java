package com.upc.pe.backend.reputation.application.internal.queryservices;

import com.upc.pe.backend.reputation.domain.model.aggregates.Review;
import com.upc.pe.backend.reputation.domain.model.commands.CreateReviewCommand;
import com.upc.pe.backend.reputation.domain.model.queries.GetAllReviewsQuery;
import com.upc.pe.backend.reputation.domain.model.queries.GetReviewByIdQuery;
import com.upc.pe.backend.reputation.domain.model.queries.GetReviewsByMechanicIdQuery;
import com.upc.pe.backend.reputation.infrastructure.persistence.jpa.repositories.ReviewRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ReviewQueryServiceImplTest {

    @Mock
    private ReviewRepository reviewRepository;

    @InjectMocks
    private ReviewQueryServiceImpl queryService;

    @Test
    void shouldReturnAllReviews() {

        Review review = new Review(
                new CreateReviewCommand(
                        "Good",
                        1L,
                        2L,
                        3L,
                        true
                )
        );

        when(reviewRepository.findAll())
                .thenReturn(List.of(review));

        var result = queryService.handle(new GetAllReviewsQuery());

        assertEquals(1, result.size());

        verify(reviewRepository).findAll();
    }

    @Test
    void shouldReturnReviewById() {

        Review review = new Review(
                new CreateReviewCommand(
                        "Excellent",
                        1L,
                        2L,
                        3L,
                        true
                )
        );

        when(reviewRepository.findById(1L))
                .thenReturn(Optional.of(review));

        var result = queryService.handle(
                new GetReviewByIdQuery(1L));

        assertTrue(result.isPresent());

        verify(reviewRepository).findById(1L);
    }

    @Test
    void shouldReturnReviewsByMechanicId() {

        Review review = new Review(
                new CreateReviewCommand(
                        "Excellent",
                        1L,
                        2L,
                        3L,
                        true
                )
        );

        when(reviewRepository.findAllByMechanicId(1L))
                .thenReturn(List.of(review));

        var result = queryService.handle(
                new GetReviewsByMechanicIdQuery(1L));

        assertEquals(1, result.size());

        verify(reviewRepository).findAllByMechanicId(1L);
    }
}
