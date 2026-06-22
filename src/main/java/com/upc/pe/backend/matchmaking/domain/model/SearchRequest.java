package com.upc.pe.backend.matchmaking.domain.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SearchRequest {

    @NotBlank(message = "La búsqueda no puede estar vacía")
    private String query;

    @NotNull(message = "La latitud del conductor es requerida")
    private Double clientLat;

    @NotNull(message = "La longitud del conductor es requerida")
    private Double clientLng;
}