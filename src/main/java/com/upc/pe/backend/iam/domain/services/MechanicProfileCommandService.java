package com.upc.pe.backend.iam.domain.services;

import com.upc.pe.backend.iam.domain.model.commands.*;
import com.upc.pe.backend.iam.domain.model.entities.MechanicProfile;
import com.upc.pe.backend.iam.domain.model.entities.Specialty;

import java.util.Optional;

public interface MechanicProfileCommandService {

    void handle(SeedSpecialtiesCommand command);

    Optional<Specialty> handle(CreateSpecialtyCommand command);

    Optional<MechanicProfile> handle(CreateMechanicProfileCommand command);

    Optional<MechanicProfile> handle(UpdateMechanicProfileCommand command);

    Optional<MechanicProfile> handle(AddSpecialtyToMechanicCommand command);

    Optional<MechanicProfile> handle(RemoveSpecialtyFromMechanicCommand command);
}