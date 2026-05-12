package com.upc.pe.backend.servicemanagement.infrastructure.persistence.jpa.repositories;

import com.upc.pe.backend.servicemanagement.domain.model.aggregates.ServiceRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ServiceRequestRepository extends JpaRepository<ServiceRequest, Long> {

    List<ServiceRequest> findAllByDriverProfileId(Long driverProfileId);

    List<ServiceRequest> findAllByMechanicProfileId(Long mechanicProfileId);
}
