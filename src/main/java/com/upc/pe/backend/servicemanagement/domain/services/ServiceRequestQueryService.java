package com.upc.pe.backend.servicemanagement.domain.services;

import com.upc.pe.backend.servicemanagement.domain.model.aggregates.ServiceRequest;
import com.upc.pe.backend.servicemanagement.domain.model.queries.*;

import java.util.List;
import java.util.Optional;

public interface ServiceRequestQueryService {

    List<ServiceRequest> handle(GetServiceHistoryQuery query);

    Optional<ServiceRequest> handle(GetServiceByIdQuery query);

    List<ServiceRequest> handle(GetServicesByDriverProfileIdQuery query);

    List<ServiceRequest> handle(GetServicesByMechanicProfileIdQuery query);
}
