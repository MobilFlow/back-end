package com.upc.pe.backend.reputation.application.internal.commandservices;

import com.upc.pe.backend.iam.infrastructure.acl.IAMContextFacade;
import com.upc.pe.backend.reputation.domain.model.aggregates.Review;
import com.upc.pe.backend.reputation.domain.model.commands.CreateReviewCommand;
import com.upc.pe.backend.reputation.domain.model.commands.UpdateReviewCommand;
import com.upc.pe.backend.reputation.infrastructure.persistence.jpa.repositories.ReviewRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ReviewCommandServiceImplTest {

    @Mock
    private ReviewRepository reviewRepository;

    @Mock
    private IAMContextFacade iamContextFacade;

    @InjectMocks
    private ReviewCommandServiceImpl commandService;

    private CreateReviewCommand createCommand;

    @BeforeEach
    void setUp() {

        createCommand = new CreateReviewCommand(
                "Excellent mechanic",
                1L,
                2L,
                3L,
                true
        );
    }

    @Test
    void shouldCreateReviewSuccessfully() {

        when(iamContextFacade.existsMechanicProfileById(1L)).thenReturn(true);
        when(reviewRepository.existsByMechanicIdAndDriverIdAndServiceId(1L,2L,3L))
                .thenReturn(false);

        Review review = new Review(createCommand);

        when(reviewRepository.save(ArgumentMatchers.any(Review.class)))
                .thenReturn(review);

        var result = commandService.handle(createCommand);

        assertTrue(result.isPresent());
        assertEquals("Excellent mechanic", result.get().getContent());

        verify(reviewRepository).save(any(Review.class));
    }

    @Test
    void shouldThrowExceptionWhenContentIsBlank() {

        CreateReviewCommand command = new CreateReviewCommand(
                "",
                1L,
                2L,
                3L,
                true
        );

        assertThrows(IllegalArgumentException.class,
                () -> commandService.handle(command));

        verify(reviewRepository, never()).save(any());
    }

    @Test
    void shouldThrowExceptionWhenServiceNotFinished() {

        CreateReviewCommand command = new CreateReviewCommand(
                "Good",
                1L,
                2L,
                3L,
                false
        );

        assertThrows(IllegalArgumentException.class,
                () -> commandService.handle(command));

        verify(reviewRepository, never()).save(any());
    }

    @Test
    void shouldThrowExceptionWhenMechanicDoesNotExist() {

        when(iamContextFacade.existsMechanicProfileById(1L))
                .thenReturn(false);

        assertThrows(IllegalArgumentException.class,
                () -> commandService.handle(createCommand));

        verify(reviewRepository, never()).save(any());
    }

    @Test
    void shouldThrowExceptionWhenReviewAlreadyExists() {

        when(iamContextFacade.existsMechanicProfileById(1L))
                .thenReturn(true);

        when(reviewRepository.existsByMechanicIdAndDriverIdAndServiceId(
                1L,2L,3L))
                .thenReturn(true);

        assertThrows(IllegalArgumentException.class,
                () -> commandService.handle(createCommand));

        verify(reviewRepository, never()).save(any());
    }

    @Test
    void shouldUpdateReviewSuccessfully() {

        Review review = new Review(createCommand);

        UpdateReviewCommand command =
                new UpdateReviewCommand(
                        1L,
                        "Updated review",
                        true
                );

        when(reviewRepository.findById(1L))
                .thenReturn(Optional.of(review));

        when(reviewRepository.save(any(Review.class)))
                .thenAnswer(i -> i.getArgument(0));

        var result = commandService.handle(command);

        assertTrue(result.isPresent());
        assertEquals("Updated review",
                result.get().getContent());

        assertTrue(result.get().getEdited());

        verify(reviewRepository).save(review);
    }

    @Test
    void shouldReturnEmptyWhenReviewNotFound() {

        when(reviewRepository.findById(1L))
                .thenReturn(Optional.empty());

        var result = commandService.handle(
                new UpdateReviewCommand(
                        1L,
                        "Updated",
                        true
                )
        );

        assertTrue(result.isEmpty());

        verify(reviewRepository, never()).save(any());
    }

    @Test
    void shouldThrowExceptionWhenUpdatedContentIsBlank() {

        UpdateReviewCommand command =
                new UpdateReviewCommand(
                        1L,
                        "",
                        true
                );

        assertThrows(IllegalArgumentException.class,
                () -> commandService.handle(command));

        verify(reviewRepository, never()).save(any());
    }
}