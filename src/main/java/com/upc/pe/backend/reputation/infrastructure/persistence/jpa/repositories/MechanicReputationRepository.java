package com.upc.pe.backend.reputation.infrastructure.persistence.jpa.repositories;

import com.upc.pe.backend.reputation.domain.model.aggregates.MechanicReputation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MechanicReputationRepository extends JpaRepository<MechanicReputation, Long> {

    Optional<MechanicReputation> findByMechanicId(Long mechanicId);
}
