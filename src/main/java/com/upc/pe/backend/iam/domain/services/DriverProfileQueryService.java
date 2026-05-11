package com.upc.pe.backend.iam.domain.services;

import com.upc.pe.backend.iam.domain.model.entities.Car;
import com.upc.pe.backend.iam.domain.model.entities.DriverProfile;
import com.upc.pe.backend.iam.domain.model.queries.GetCarByIdQuery;
import com.upc.pe.backend.iam.domain.model.queries.GetCarsByDriverProfileIdQuery;
import com.upc.pe.backend.iam.domain.model.queries.GetDriverProfileByUserIdQuery;

import java.util.List;
import java.util.Optional;

public interface DriverProfileQueryService {

    Optional<Car> handle(GetCarByIdQuery query);

    List<Car> handle(GetCarsByDriverProfileIdQuery query);

    Optional<DriverProfile> handle(GetDriverProfileByUserIdQuery query);
}