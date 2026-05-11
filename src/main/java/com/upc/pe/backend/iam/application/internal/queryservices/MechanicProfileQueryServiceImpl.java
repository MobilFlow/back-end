package com.upc.pe.backend.iam.application.internal.queryservices;


import com.upc.pe.backend.iam.domain.model.entities.MechanicProfile;
import com.upc.pe.backend.iam.domain.model.entities.Specialty;
import com.upc.pe.backend.iam.domain.model.queries.GetAllMechanicProfilesQuery;
import com.upc.pe.backend.iam.domain.model.queries.GetAllSpecialtiesQuery;
import com.upc.pe.backend.iam.domain.model.queries.GetMechanicProfileByUserIdQuery;
import com.upc.pe.backend.iam.domain.model.queries.GetSpecialtyByIdQuery;
import com.upc.pe.backend.iam.domain.services.MechanicProfileQueryService;
import com.upc.pe.backend.iam.infrastructure.persistence.jpa.repositories.MechanicProfileRepository;
import com.upc.pe.backend.iam.infrastructure.persistence.jpa.repositories.SpecialtyRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MechanicProfileQueryServiceImpl implements MechanicProfileQueryService {

    private final MechanicProfileRepository mechanicProfileRepository;
    private final SpecialtyRepository specialtyRepository;

    public MechanicProfileQueryServiceImpl(
            MechanicProfileRepository mechanicProfileRepository,
            SpecialtyRepository specialtyRepository
    ) {
        this.mechanicProfileRepository = mechanicProfileRepository;
        this.specialtyRepository = specialtyRepository;
    }

    @Override
    public List<MechanicProfile> handle(GetAllMechanicProfilesQuery query) {
        return mechanicProfileRepository.findAll();
    }

    @Override
    public Optional<MechanicProfile> handle(GetMechanicProfileByUserIdQuery query) {
        return mechanicProfileRepository.findByUserId(query.userId());
    }

    @Override
    public List<Specialty> handle(GetAllSpecialtiesQuery query) {
        return specialtyRepository.findAll();
    }

    @Override
    public Optional<Specialty> handle(GetSpecialtyByIdQuery query) {
        return specialtyRepository.findById(query.specialtyId());
    }
}