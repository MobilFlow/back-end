package com.upc.pe.backend.matchmaking.interfaces;

import com.upc.pe.backend.matchmaking.domain.model.Mechanic;
import com.upc.pe.backend.matchmaking.infrastructure.MechanicRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/mechanics")
@RequiredArgsConstructor
public class MechanicController {

    private final MechanicRepository mechanicRepository;

    @GetMapping
    public ResponseEntity<List<Mechanic>> getAll() {
        return ResponseEntity.ok(mechanicRepository.findByDisponibleTrue());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Mechanic> getById(@PathVariable Long id) {
        return mechanicRepository.findAll().stream()
                .filter(m -> m.getId().equals(id))
                .findFirst()
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}