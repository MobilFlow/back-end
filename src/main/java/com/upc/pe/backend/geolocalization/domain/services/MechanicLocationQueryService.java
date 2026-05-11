package com.upc.pe.backend.geolocalization.domain.services;

import com.upc.pe.backend.geolocalization.domain.model.aggregates.MechanicLocation;
import com.upc.pe.backend.geolocalization.domain.model.queries.GetAllMechanicLocationsQuery;
import com.upc.pe.backend.geolocalization.domain.model.queries.GetMechanicLocationQuery;

import java.util.List;
import java.util.Optional;

public interface MechanicLocationQueryService {

    List<MechanicLocation> handle(GetAllMechanicLocationsQuery query);

    Optional<MechanicLocation> handle(GetMechanicLocationQuery query);

}