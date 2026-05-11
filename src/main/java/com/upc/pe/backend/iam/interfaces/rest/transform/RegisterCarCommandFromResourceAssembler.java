package com.upc.pe.backend.iam.interfaces.rest.transform;

import com.upc.pe.backend.iam.domain.model.commands.RegisterCarCommand;
import com.upc.pe.backend.iam.domain.model.valueobjects.FuelType;
import com.upc.pe.backend.iam.interfaces.rest.resources.RegisterCarResource;

public class RegisterCarCommandFromResourceAssembler {

    public static RegisterCarCommand toCommandFromResource(
            Long driverProfileId,
            RegisterCarResource resource
    ) {
        return new RegisterCarCommand(
                driverProfileId,
                resource.brand(),
                resource.model(),
                resource.year(),
                resource.plate(),
                FuelType.valueOf(resource.fuelType())
        );
    }
}