package com.upc.pe.backend.matchmaking.application;

import com.upc.pe.backend.matchmaking.domain.model.Mechanic;
import com.upc.pe.backend.matchmaking.domain.model.SearchRequest;
import com.upc.pe.backend.matchmaking.domain.model.SearchResult;
import com.upc.pe.backend.matchmaking.infrastructure.MechanicRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MechanicSearchService {

    private final OpenAIService openAIService;
    private final ScoringService scoringService;
    private final MechanicRepository mechanicRepository;

    public List<SearchResult> buscar(SearchRequest request) {

        // 1. openai detecta la etiqueta desde el texto del conductor
        String tag = openAIService.detectarEtiqueta(request.getQuery());

        // 2. filtra mecánicos que tengan esa etiqueta y estén disponibles
        List<Mechanic> mecanicos;
        if ("desconocido".equals(tag)) {
            mecanicos = mechanicRepository.findByDisponibleTrue();
        } else {
            mecanicos = mechanicRepository.findByEtiquetaAndDisponible(tag);
        }

        // 3. aplica scoring: tag match + distancia + rating
        return scoringService.rankear(mecanicos, tag, request.getClientLat(), request.getClientLng());
    }
}