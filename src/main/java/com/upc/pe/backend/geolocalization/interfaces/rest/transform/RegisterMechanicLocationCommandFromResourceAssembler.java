package com.upc.pe.backend.geolocalization.interfaces.rest.transform;

import com.upc.pe.backend.geolocalization.domain.model.commands.RegisterMechanicLocationCommand;
import com.upc.pe.backend.geolocalization.interfaces.rest.resources.RegisterMechanicLocationResource;

public class RegisterMechanicLocationCommandFromResourceAssembler {

    public static RegisterMechanicLocationCommand toCommandFromResource(
            Long mechanicId,
            RegisterMechanicLocationResource resource
    ) {

        return new RegisterMechanicLocationCommand(
                mechanicId,
                resource.latitude(),
                resource.longitude(),
                resource.addressText()
        );
    }
}