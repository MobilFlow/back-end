package com.upc.pe.backend.iam.interfaces.rest.transform;

import com.upc.pe.backend.iam.domain.model.commands.UpdateCarCommand;
import com.upc.pe.backend.iam.domain.model.valueobjects.FuelType;
import com.upc.pe.backend.iam.interfaces.rest.resources.UpdateCarResource;

public class UpdateCarCommandFromResourceAssembler {

    public static UpdateCarCommand toCommandFromResource(
            Long carId,
            UpdateCarResource resource
    ) {
        return new UpdateCarCommand(
                carId,
                resource.brand(),
                resource.model(),
                resource.year(),
                resource.plate(),
                FuelType.valueOf(resource.fuelType())
        );
    }
}