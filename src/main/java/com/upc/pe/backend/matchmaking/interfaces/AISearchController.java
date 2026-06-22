package com.upc.pe.backend.matchmaking.interfaces;

import com.upc.pe.backend.matchmaking.application.MechanicSearchService;
import com.upc.pe.backend.matchmaking.application.ScoringService;
import com.upc.pe.backend.matchmaking.application.TagResolverService;
import com.upc.pe.backend.matchmaking.domain.model.Mechanic;
import com.upc.pe.backend.matchmaking.domain.model.SearchRequest;
import com.upc.pe.backend.matchmaking.domain.model.SearchResult;
import com.upc.pe.backend.matchmaking.infrastructure.MechanicRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/matchmaking")
@RequiredArgsConstructor
public class AISearchController {

    private final MechanicSearchService mechanicSearchService;
    private final TagResolverService tagResolverService;
    private final ScoringService scoringService;
    private final MechanicRepository mechanicRepository;

    // endpoint original - usa OpenAI
    @PostMapping("/search/ai")
    public ResponseEntity<List<SearchResult>> searchAi(@Valid @RequestBody SearchRequest request) {
        return ResponseEntity.ok(mechanicSearchService.buscar(request));
    }

    // endpoint nuevo - usa diccionario local
    @PostMapping("/search/quick")
    public ResponseEntity<List<SearchResult>> searchQuick(@Valid @RequestBody SearchRequest request) {
        String tag = tagResolverService.resolver(request.getQuery());

        List<Mechanic> mecanicos = "desconocido".equals(tag)
                ? mechanicRepository.findByDisponibleTrue()
                : mechanicRepository.findByEtiquetaAndDisponible(tag);

        List<SearchResult> results = scoringService.rankear(
                mecanicos, tag, request.getClientLat(), request.getClientLng()
        );

        return ResponseEntity.ok(results);
    }
}