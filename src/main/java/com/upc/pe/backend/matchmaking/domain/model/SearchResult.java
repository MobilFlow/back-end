package com.upc.pe.backend.matchmaking.domain.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SearchResult {
    private Long id;
    private Long userId;
    private String fullName;
    private String profilePicture;
    private Double rating;
    private Double lat;
    private Double lng;
    private List<String> etiquetas;
    private String tagDetectado;
    private Integer tagScore;
    private Double distanciaKm;
}