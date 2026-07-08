package com.upc.pe.backend.servicemanagement.application.internal.services;

import com.upc.pe.backend.servicemanagement.domain.services.DiagnosisGeneratorService;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class DiagnosisGeneratorServiceImpl implements DiagnosisGeneratorService {

    private static final Map<String, List<String>> RULES = new LinkedHashMap<>();

    static {
        // Priority order is defined by the insertion order in LinkedHashMap
        RULES.put("Transmission", Arrays.asList("caja", "cambios", "transmisión", "transmision", "embrague", "clutch", "marchas"));
        RULES.put("Engine", Arrays.asList("motor", "engine", "humo", "sobrecalentamiento", "pistones", "no arranca", "fuerza"));
        RULES.put("Brakes", Arrays.asList("frenos", "brakes", "ruido al frenar", "pastillas", "discos", "pedal esponjoso"));
        RULES.put("Electrical", Arrays.asList("luces", "batería", "battery", "alternador", "corto", "fusibles", "tablero"));
        RULES.put("Suspension", Arrays.asList("amortiguadores", "suspensión", "suspension", "golpeteo", "dirección", "direccion", "alineación", "alineacion"));
        RULES.put("Oil Change", Arrays.asList("aceite", "oil", "filtro", "cambio de aceite", "lubricación", "lubricacion"));
    }

    @Override
    public String generateSummary(String description, String recommendedSpecialty) {
        if (recommendedSpecialty.equals("General Mechanics")) {
            return "Preliminary diagnosis: Non-specific symptoms detected. A general technical inspection is required.";
        }
        return String.format("Automatic analysis: Potential issue with %s detected based on reported symptoms.", recommendedSpecialty);
    }

    @Override
    public String recommendSpecialty(String description) {
        if (description == null || description.isBlank()) {
            return "General Mechanics";
        }

        String lowerDescription = description.toLowerCase();
        String bestMatch = "General Mechanics";
        int maxHits = 0;

        for (Map.Entry<String, List<String>> entry : RULES.entrySet()) {
            int hits = 0;
            for (String keyword : entry.getValue()) {
                if (lowerDescription.contains(keyword.toLowerCase())) {
                    hits++;
                }
            }
            if (hits > maxHits) {
                maxHits = hits;
                bestMatch = entry.getKey();
            } else if (hits == maxHits && hits > 0) {
                // Tie-breaker: already handled by LinkedHashMap order if we only update on strictly greater
                // But let's be explicit: first one in RULES wins in case of tie.
            }
        }

        return bestMatch;
    }
}
