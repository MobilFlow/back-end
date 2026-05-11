package com.upc.pe.backend.iam.interfaces.rest.transform;

import com.upc.pe.backend.iam.domain.model.entities.Car;
import com.upc.pe.backend.iam.interfaces.rest.resources.CarResource;

public class CarResourceFromEntityAssembler {

    public static CarResource toResourceFromEntity(Car entity) {
        return new CarResource(
                entity.getId(),
                entity.getOwnerId(),
                entity.getBrand(),
                entity.getModel(),
                entity.getYear(),
                entity.getPlate(),
                entity.getFuelType().toString()
        );
    }
}