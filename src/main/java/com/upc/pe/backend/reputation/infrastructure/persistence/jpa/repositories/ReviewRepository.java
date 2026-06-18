package com.upc.pe.backend.reputation.infrastructure.persistence.jpa.repositories;

import com.upc.pe.backend.reputation.domain.model.aggregates.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {

    boolean existsByMechanicIdAndDriverIdAndServiceId(Long mechanicId, Long driverId, Long serviceId);

    List<Review> findAllByMechanicId(Long mechanicId);

    long countByMechanicId(Long mechanicId);
}
