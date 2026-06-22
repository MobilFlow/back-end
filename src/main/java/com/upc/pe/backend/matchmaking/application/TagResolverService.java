package com.upc.pe.backend.matchmaking.application;

import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class TagResolverService {

    private static final Map<String, String> DICTIONARY = Map.of(
            "frenos", "frenos",
            "aceite", "oil_change",
            "cambio de aceite", "oil_change",
            "motor", "engine",
            "transmision", "transmision",
            "caja", "transmision",
            "suspension", "suspension",
            "sedan", "sedan",
            "suv", "SUV"
    );

    public String resolver(String query) {
        String normalized = query.toLowerCase().trim();

        // coincidencia exacta
        if (DICTIONARY.containsKey(normalized)) {
            return DICTIONARY.get(normalized);
        }

        // coincidencia parcial
        return DICTIONARY.entrySet().stream()
                .filter(entry -> normalized.contains(entry.getKey()))
                .map(Map.Entry::getValue)
                .findFirst()
                .orElse("desconocido");
    }
}