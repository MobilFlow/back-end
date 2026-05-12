package com.upc.pe.backend.servicemanagement.application.internal.queryservices;

import com.upc.pe.backend.servicemanagement.domain.model.aggregates.ServiceRequest;
import com.upc.pe.backend.servicemanagement.domain.model.queries.*;
import com.upc.pe.backend.servicemanagement.domain.services.ServiceRequestQueryService;
import com.upc.pe.backend.servicemanagement.infrastructure.persistence.jpa.repositories.ServiceRequestRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ServiceRequestQueryServiceImpl implements ServiceRequestQueryService {

    private final ServiceRequestRepository serviceRequestRepository;

    public ServiceRequestQueryServiceImpl(
            ServiceRequestRepository serviceRequestRepository
    ) {
        this.serviceRequestRepository = serviceRequestRepository;
    }

    @Override
    public List<ServiceRequest> handle(GetServiceHistoryQuery query) {
        return serviceRequestRepository.findAllByDriverProfileId(query.driverProfileId());
    }

    @Override
    public Optional<ServiceRequest> handle(GetServiceByIdQuery query) {
        return serviceRequestRepository.findById(query.serviceId());
    }

    @Override
    public List<ServiceRequest> handle(GetServicesByDriverProfileIdQuery query) {
        return serviceRequestRepository.findAllByDriverProfileId(query.driverProfileId());
    }

    @Override
    public List<ServiceRequest> handle(GetServicesByMechanicProfileIdQuery query) {
        return serviceRequestRepository.findAllByMechanicProfileId(query.mechanicProfileId());
    }
}
