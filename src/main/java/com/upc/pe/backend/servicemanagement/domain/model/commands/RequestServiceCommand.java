package com.upc.pe.backend.servicemanagement.domain.model.commands;

import java.util.Date;

public record RequestServiceCommand(
        Long driverProfileId,
        Long mechanicProfileId,
        Long carId,
        String description,
        Date scheduledDate
) {
}
