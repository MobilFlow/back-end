package com.upc.pe.backend.reputation.interfaces.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.upc.pe.backend.reputation.domain.model.aggregates.Review;
import com.upc.pe.backend.reputation.domain.model.commands.CreateReviewCommand;
import com.upc.pe.backend.reputation.domain.model.commands.UpdateReviewCommand;
import com.upc.pe.backend.reputation.domain.services.RatingCommandService;
import com.upc.pe.backend.reputation.domain.services.RatingQueryService;
import com.upc.pe.backend.reputation.domain.services.ReputationQueryService;
import com.upc.pe.backend.reputation.domain.services.ReviewCommandService;
import com.upc.pe.backend.reputation.domain.services.ReviewQueryService;
import com.upc.pe.backend.reputation.interfaces.rest.resources.CreateReviewResource;
import com.upc.pe.backend.reputation.interfaces.rest.resources.UpdateReviewResource;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Date;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(ReputationController.class)
@AutoConfigureMockMvc(addFilters = false)
class ReputationControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private RatingCommandService ratingCommandService;

    @MockBean
    private RatingQueryService ratingQueryService;

    @MockBean
    private ReviewCommandService reviewCommandService;

    @MockBean
    private ReviewQueryService reviewQueryService;

    @MockBean
    private ReputationQueryService reputationQueryService;

    @Test
    void shouldCreateReviewSuccessfully() throws Exception {

        CreateReviewResource request = new CreateReviewResource(
                "Excellent mechanic",
                1L,
                2L,
                10L,
                true
        );

        Review review = new Review(
                "Excellent mechanic",
                1L,
                2L,
                10L
        );

        when(reviewCommandService.handle(any(CreateReviewCommand.class)))
                .thenReturn(Optional.of(review));

        mockMvc.perform(post("/api/v1/reputation/reviews")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content").value("Excellent mechanic"))
                .andExpect(jsonPath("$.mechanicId").value(1))
                .andExpect(jsonPath("$.driverId").value(2))
                .andExpect(jsonPath("$.serviceId").value(10))
                .andExpect(jsonPath("$.edited").value(false));
    }

    @Test
    void shouldUpdateReviewSuccessfully() throws Exception {

        UpdateReviewResource request = new UpdateReviewResource(
                "Updated review",
                true
        );

        Review review = new Review(
                "Updated review",
                1L,
                2L,
                10L
        );


        review.setEdited(true);

        when(reviewCommandService.handle(any(UpdateReviewCommand.class)))
                .thenReturn(Optional.of(review));

        mockMvc.perform(put("/api/v1/reputation/reviews/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content").value("Updated review"))
                .andExpect(jsonPath("$.edited").value(true));
    }
}