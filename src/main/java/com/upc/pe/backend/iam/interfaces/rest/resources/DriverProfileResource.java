package com.upc.pe.backend.iam.interfaces.rest.resources;

import java.time.LocalDate;
import java.util.List;

public record DriverProfileResource<CarResource>(
        Long id,
        Long userId,
        List<CarResource> cars
) {}