package com.upc.pe.backend.servicemanagement.interfaces.rest.transform;

import com.upc.pe.backend.servicemanagement.domain.model.commands.RequestServiceCommand;
import com.upc.pe.backend.servicemanagement.interfaces.rest.resources.CreateServiceRequestResource;

public class CreateServiceRequestCommandFromResourceAssembler {

    public static RequestServiceCommand toCommandFromResource(CreateServiceRequestResource resource) {
        return new RequestServiceCommand(
                resource.serviceId(),
                resource.driverProfileId(),
                resource.mechanicProfileId(),
                resource.carId(),
                resource.description(),
                resource.scheduledDate()
        );
    }
}
