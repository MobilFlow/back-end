package com.upc.pe.backend.iam.domain.services;

import com.upc.pe.backend.iam.domain.model.entities.MechanicProfile;
import com.upc.pe.backend.iam.domain.model.entities.Specialty;
import com.upc.pe.backend.iam.domain.model.queries.GetAllMechanicProfilesQuery;
import com.upc.pe.backend.iam.domain.model.queries.GetAllSpecialtiesQuery;
import com.upc.pe.backend.iam.domain.model.queries.GetMechanicProfileByUserIdQuery;
import com.upc.pe.backend.iam.domain.model.queries.GetSpecialtyByIdQuery;

import java.util.List;
import java.util.Optional;

public interface MechanicProfileQueryService {

    List<MechanicProfile> handle(GetAllMechanicProfilesQuery query);

    Optional<MechanicProfile> handle(GetMechanicProfileByUserIdQuery query);

    List<Specialty> handle(GetAllSpecialtiesQuery query);

    Optional<Specialty> handle(GetSpecialtyByIdQuery query);
}