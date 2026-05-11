package com.upc.pe.backend.reputation.infrastructure.persistence.jpa.repositories;

import com.upc.pe.backend.reputation.domain.model.aggregates.Rating;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RatingRepository extends JpaRepository<Rating, Long> {

    boolean existsByMechanicIdAndDriverIdAndServiceId(Long mechanicId, Long driverId, Long serviceId);

    List<Rating> findAllByMechanicId(Long mechanicId);
}
