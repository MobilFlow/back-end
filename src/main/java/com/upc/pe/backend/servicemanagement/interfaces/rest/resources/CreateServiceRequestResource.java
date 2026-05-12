package com.upc.pe.backend.servicemanagement.interfaces.rest.resources;

import java.util.Date;

public record CreateServiceRequestResource(
        Long driverProfileId,
        Long mechanicProfileId,
        Long carId,
        String description,
        Date scheduledDate
) {
}
