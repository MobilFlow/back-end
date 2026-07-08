package com.upc.pe.backend.matchmaking.infrastructure;

import com.upc.pe.backend.matchmaking.domain.model.Mechanic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MechanicRepository extends JpaRepository<Mechanic, Long> {

    @Query("SELECT m FROM Mechanic m JOIN m.tags t WHERE t.name = :etiqueta")
    List<Mechanic> findByEtiquetaAndDisponible(@Param("etiqueta") String etiqueta);

    @Query("SELECT m FROM Mechanic m")
    List<Mechanic> findByDisponibleTrue();
}