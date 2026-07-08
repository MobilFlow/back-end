package com.upc.pe.backend.servicemanagement.application.internal.services;

import com.upc.pe.backend.iam.infrastructure.acl.IAMContextFacade;
import com.upc.pe.backend.iam.infrastructure.acl.MechanicMatchingCandidateDTO;
import com.upc.pe.backend.reputation.infrastructure.acl.ReputationContextFacade;
import com.upc.pe.backend.servicemanagement.domain.model.aggregates.Diagnosis;
import com.upc.pe.backend.servicemanagement.domain.services.MatchingService;
import com.upc.pe.backend.servicemanagement.infrastructure.persistence.jpa.repositories.DiagnosisRepository;
import com.upc.pe.backend.servicemanagement.interfaces.rest.resources.RecommendationResource;
import org.springframework.stereotype.Service;

import java.text.Normalizer;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class MatchingServiceImpl implements MatchingService {

    private final DiagnosisRepository diagnosisRepository;
    private final IAMContextFacade iamContextFacade;
    private final ReputationContextFacade reputationContextFacade;

    private static final Map<String, List<String>> ALIASES = new HashMap<>();

    static {
        ALIASES.put("Transmission", Arrays.asList("caja de cambios", "transmision", "embrague", "transmission", "clutch"));
        ALIASES.put("Engine", Arrays.asList("motor", "mecanica de motores", "engine", "culata", "pistones"));
        ALIASES.put("Brakes", Arrays.asList("frenos", "sistema de frenos", "brakes", "pastillas", "discos"));
        ALIASES.put("Electrical", Arrays.asList("luces", "bateria", "electrical", "sistema electrico", "alternador"));
        ALIASES.put("Suspension", Arrays.asList("amortiguadores", "suspension", "direccion", "suspension y direccion"));
        ALIASES.put("Oil Change", Arrays.asList("aceite", "mantenimiento preventivo", "oil change", "lubricacion"));
        ALIASES.put("General Mechanics", Arrays.asList("mecanica general", "general mechanics", "mantenimiento", "inspeccion"));
    }

    public MatchingServiceImpl(DiagnosisRepository diagnosisRepository,
                               IAMContextFacade iamContextFacade,
                               ReputationContextFacade reputationContextFacade) {
        this.diagnosisRepository = diagnosisRepository;
        this.iamContextFacade = iamContextFacade;
        this.reputationContextFacade = reputationContextFacade;
    }

    @Override
    public List<RecommendationResource> getRecommendations(Long serviceRequestId) {
        Optional<Diagnosis> diagnosisOpt = diagnosisRepository.findByServiceRequestId(serviceRequestId);
        if (diagnosisOpt.isEmpty()) {
            return Collections.emptyList();
        }

        String recommendedSpecialty = diagnosisOpt.get().getRecommendedSpecialty();
        return getRecommendationsBySpecialty(recommendedSpecialty);
    }

    @Override
    public List<RecommendationResource> getRecommendationsBySpecialty(String recommendedSpecialty) {
        List<MechanicMatchingCandidateDTO> candidates = iamContextFacade.getAllMechanicMatchingCandidates();

        return candidates.stream()
                .map(candidate -> tryMatch(candidate, recommendedSpecialty))
                .filter(Objects::nonNull)
                .sorted(Comparator.comparing(RecommendationResource::score).reversed())
                .limit(3)
                .collect(Collectors.toList());
    }

    private RecommendationResource tryMatch(MechanicMatchingCandidateDTO candidate, String recommendedSpecialty) {
        String matchedName = null;
        boolean found = false;

        List<String> aliases = ALIASES.getOrDefault(recommendedSpecialty, Collections.singletonList(normalize(recommendedSpecialty)));

        for (String specialtyName : candidate.specialties()) {
            String normalizedName = normalize(specialtyName);
            for (String alias : aliases) {
                if (normalizedName.contains(normalize(alias)) || normalize(alias).contains(normalizedName)) {
                    matchedName = specialtyName;
                    found = true;
                    break;
                }
            }
            if (found) break;
        }

        if (!found) return null;

        var reputationOpt = reputationContextFacade.getReputationByMechanicId(candidate.mechanicProfileId());
        Double averageScore = 0.0;
        Integer ratingsCount = 0;
        Double finalScore = 0.0;

        if (reputationOpt.isPresent()) {
            averageScore = reputationOpt.get().averageScore();
            ratingsCount = reputationOpt.get().ratingsCount();
            finalScore = calculateScore(averageScore, ratingsCount);
        }

        return new RecommendationResource(
                candidate.mechanicProfileId(),
                candidate.workshopName(),
                matchedName,
                averageScore,
                ratingsCount,
                finalScore
        );
    }

    private Double calculateScore(Double averageScore, Integer ratingsCount) {
        double ratingScore = averageScore / 5.0;
        double reliabilityScore = Math.min(Math.log10(ratingsCount + 1) / 2.0, 1.0);
        return (ratingScore * 0.8) + (reliabilityScore * 0.2);
    }

    private String normalize(String input) {
        if (input == null) return "";
        String normalized = Normalizer.normalize(input, Normalizer.Form.NFD);
        return normalized.replaceAll("[\\p{InCombiningDiacriticalMarks}]", "").toLowerCase().trim();
    }
}
