package com.upc.pe.backend.servicemanagement.interfaces.rest.resources;

import com.upc.pe.backend.servicemanagement.domain.model.valueobjects.ServiceStatus;

import java.util.Date;

public record ServiceRequestResource(
        Long id,
        Long driverProfileId,
        Long mechanicProfileId,
        Long carId,
        String description,
        Date scheduledDate,
        ServiceStatus status,
        Boolean driverConfirmed,
        Boolean mechanicConfirmed,
        Date completedAt,
        Date createdAt
) {
}
