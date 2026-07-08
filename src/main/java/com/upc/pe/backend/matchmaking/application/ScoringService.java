package com.upc.pe.backend.matchmaking.application;

import com.upc.pe.backend.geolocalization.domain.model.aggregates.MechanicLocation;
import com.upc.pe.backend.geolocalization.infrastructure.persistance.jpa.repositories.MechanicLocationRepository;
import com.upc.pe.backend.matchmaking.domain.model.Mechanic;
import com.upc.pe.backend.matchmaking.domain.model.SearchResult;
import com.upc.pe.backend.matchmaking.domain.service.GeoUtils;
import com.upc.pe.backend.reputation.domain.model.aggregates.MechanicReputation;
import com.upc.pe.backend.reputation.infrastructure.persistence.jpa.repositories.MechanicReputationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ScoringService {

    private final MechanicLocationRepository locationRepository;
    private final MechanicReputationRepository reputationRepository;

    public List<SearchResult> rankear(List<Mechanic> mecanicos, String tag, double clientLat, double clientLng) {
        return mecanicos.stream()
                .map(m -> toSearchResult(m, tag, clientLat, clientLng))
                .sorted(
                        Comparator.comparingInt(SearchResult::getTagScore).reversed()
                                .thenComparingDouble(SearchResult::getDistanciaKm)
                                .thenComparingDouble(r -> -r.getRating())
                )
                .toList();
    }

    private SearchResult toSearchResult(Mechanic m, String tag, double clientLat, double clientLng) {

        List<String> etiquetas = m.getTags().stream()
                .map(t -> t.getName())
                .toList();

        int tagScore = etiquetas.contains(tag) ? 1 : 0;

        // busca location y reputation por mechanicId
        Optional<MechanicLocation> location = locationRepository.findByMechanicId(m.getId());
        Optional<MechanicReputation> reputation = reputationRepository.findByMechanicId(m.getId());

        double lat = location.map(MechanicLocation::getLatitude).orElse(0.0);
        double lng = location.map(MechanicLocation::getLongitude).orElse(0.0);
        double rating = reputation.map(MechanicReputation::getAverageScore).orElse(0.0);

        double distancia = GeoUtils.haversine(clientLat, clientLng, lat, lng);

        return new SearchResult(
                m.getId(),
                m.getUser().getId(),
                m.getUser().getFullName(),
                m.getUser().getProfilePicture(),
                rating,
                lat,
                lng,
                etiquetas,
                tag,
                tagScore,
                distancia
        );
    }
}