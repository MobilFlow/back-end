package com.upc.pe.backend.geolocalization.application.internal.queryservices;

import com.upc.pe.backend.geolocalization.domain.model.aggregates.MechanicLocation;
import com.upc.pe.backend.geolocalization.domain.model.queries.GetAllMechanicLocationsQuery;
import com.upc.pe.backend.geolocalization.domain.model.queries.GetMechanicLocationQuery;
import com.upc.pe.backend.geolocalization.domain.services.MechanicLocationQueryService;
import com.upc.pe.backend.geolocalization.infrastructure.persistance.jpa.repositories.MechanicLocationRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MechanicLocationQueryServiceImpl
        implements MechanicLocationQueryService {

    private final MechanicLocationRepository mechanicLocationRepository;

    public MechanicLocationQueryServiceImpl(
            MechanicLocationRepository mechanicLocationRepository
    ) {
        this.mechanicLocationRepository = mechanicLocationRepository;
    }

    @Override
    public List<MechanicLocation> handle(GetAllMechanicLocationsQuery query) {
        return mechanicLocationRepository.findAll();
    }

    @Override
    public Optional<MechanicLocation> handle(GetMechanicLocationQuery query) {
        return mechanicLocationRepository.findByMechanicId(query.mechanicId());
    }

}