package com.upc.pe.backend.servicemanagement.interfaces.rest.transform;

import com.upc.pe.backend.servicemanagement.domain.model.aggregates.ServiceRequest;
import com.upc.pe.backend.servicemanagement.interfaces.rest.resources.ServiceRequestResource;

public class ServiceRequestResourceFromEntityAssembler {

    public static ServiceRequestResource toResourceFromEntity(ServiceRequest entity) {
        return new ServiceRequestResource(
                entity.getId(),
                entity.getDriverProfileId(),
                entity.getMechanicProfileId(),
                entity.getCarId(),
                entity.getDescription(),
                entity.getScheduledDate(),
                entity.getStatus(),
                entity.getDriverConfirmed(),
                entity.getMechanicConfirmed(),
                entity.getCompletedAt(),
                entity.getCreatedAt()
        );
    }
}
