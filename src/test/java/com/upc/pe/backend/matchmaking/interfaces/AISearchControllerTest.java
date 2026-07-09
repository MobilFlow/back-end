package com.upc.pe.backend.matchmaking.interfaces;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.upc.pe.backend.matchmaking.application.MechanicSearchService;
import com.upc.pe.backend.matchmaking.application.ScoringService;
import com.upc.pe.backend.matchmaking.application.TagResolverService;
import com.upc.pe.backend.matchmaking.domain.model.SearchRequest;
import com.upc.pe.backend.matchmaking.domain.model.SearchResult;
import com.upc.pe.backend.matchmaking.infrastructure.MechanicRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(AISearchController.class)
@AutoConfigureMockMvc(addFilters = false)
class AISearchControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private MechanicSearchService mechanicSearchService;

    @MockBean
    private TagResolverService tagResolverService;

    @MockBean
    private ScoringService scoringService;

    @MockBean
    private MechanicRepository mechanicRepository;

    @Test
    void shouldSearchUsingAI() throws Exception {

        SearchRequest request = new SearchRequest(
                "My brakes are making noise",
                -12.05,
                -77.04
        );

        SearchResult result = new SearchResult(
                1L,
                5L,
                "Max Garage",
                "photo.jpg",
                4.8,
                -12.05,
                -77.04,
                List.of("brakes"),
                "brakes",
                95,
                1.3
        );

        when(mechanicSearchService.buscar(any(SearchRequest.class)))
                .thenReturn(List.of(result));
        mockMvc.perform(post("/api/v1/matchmaking/search/ai")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].mechanicId").value(1))
                .andExpect(jsonPath("$[0].workshopName").value("Max Garage"));

        verify(mechanicSearchService).buscar(any(SearchRequest.class));
    }

    @Test
    void shouldSearchUsingQuickDictionary() throws Exception {

        SearchRequest request = new SearchRequest(
                "Brake problem",
                -12.05,
                -77.04
        );

        when(tagResolverService.resolver(any()))
                .thenReturn("brakes");

        when(mechanicRepository.findByEtiquetaAndDisponible("brakes"))
                .thenReturn(List.of());

        when(scoringService.rankear(any(), any(), anyDouble(), anyDouble()))
                .thenReturn(List.of());

        mockMvc.perform(post("/api/v1/matchmaking/search/quick")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk());

        verify(tagResolverService).resolver(any());
        verify(scoringService).rankear(any(), eq("brakes"), anyDouble(), anyDouble());
    }
}