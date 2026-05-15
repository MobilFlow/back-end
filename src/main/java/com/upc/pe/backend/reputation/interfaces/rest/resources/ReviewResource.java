package com.upc.pe.backend.reputation.interfaces.rest.resources;

import java.util.Date;

public record ReviewResource(
        Long id,
        String content,
        Long mechanicId,
        Long driverId,
        Long serviceId,
        Date createdAt,
        Boolean edited
) {
}
