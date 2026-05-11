package com.upc.pe.backend.iam.application.internal.commandservices;

import com.upc.pe.backend.iam.domain.model.commands.*;
import com.upc.pe.backend.iam.domain.model.entities.MechanicProfile;
import com.upc.pe.backend.iam.domain.model.entities.Specialty;
import com.upc.pe.backend.iam.domain.services.MechanicProfileCommandService;
import com.upc.pe.backend.iam.infrastructure.persistence.jpa.repositories.MechanicProfileRepository;
import com.upc.pe.backend.iam.infrastructure.persistence.jpa.repositories.SpecialtyRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MechanicProfileCommandServiceImpl implements MechanicProfileCommandService {

    private static final List<String> DEFAULT_SPECIALTIES = List.of(
            "Brake Repair", "Hybrid Systems", "Engine Repair",
            "Suspension", "Oil Change", "Electrical Systems",
            "Transmission", "Air Conditioning"
    );

    private final MechanicProfileRepository mechanicProfileRepository;
    private final SpecialtyRepository specialtyRepository;

    public MechanicProfileCommandServiceImpl(MechanicProfileRepository mechanicProfileRepository,
                                             SpecialtyRepository specialtyRepository) {
        this.mechanicProfileRepository = mechanicProfileRepository;
        this.specialtyRepository       = specialtyRepository;
    }

    /** Seeds the system with the default specialties if not already present. */
    @Override
    public void handle(SeedSpecialtiesCommand command) {
        DEFAULT_SPECIALTIES.forEach(name -> {
            if (!specialtyRepository.existsByName(name)) {
                specialtyRepository.save(new Specialty(name));
            }
        });
    }

    /** Creates a new Specialty. */
    @Override
    public Optional<Specialty> handle(CreateSpecialtyCommand command) {
        if (specialtyRepository.existsByName(command.name()))
            throw new IllegalArgumentException(
                    String.format("Specialty '%s' already exists", command.name()));
        return Optional.of(specialtyRepository.save(new Specialty(command.name())));
    }

    /** Creates a MechanicProfile for an existing User. */
    @Override
    @Transactional
    public Optional<MechanicProfile> handle(CreateMechanicProfileCommand command) {
        if (mechanicProfileRepository.existsByUserId(command.userId()))
            throw new IllegalStateException(
                    String.format("Mechanic profile already exists for user %d", command.userId()));

        var profile = new MechanicProfile(
                command.userId(),
                command.description(),
                command.workshopName(),
                command.workshopAddress()
        );
        return Optional.of(mechanicProfileRepository.save(profile));
    }

    /** Updates workshop info on an existing MechanicProfile. */
    @Override
    public Optional<MechanicProfile> handle(UpdateMechanicProfileCommand command) {
        var profile = mechanicProfileRepository.findById(command.mechanicProfileId())
                .orElseThrow(() -> new IllegalArgumentException(
                        String.format("MechanicProfile %d not found", command.mechanicProfileId())));

        profile.setDescription(command.description());
        profile.setWorkshopName(command.workshopName());
        profile.setWorkshopAddress(command.workshopAddress());
        return Optional.of(mechanicProfileRepository.save(profile));
    }

    /** Adds an existing Specialty to a MechanicProfile. */
    @Override
    @Transactional
    public Optional<MechanicProfile> handle(AddSpecialtyToMechanicCommand command) {
        var profile = mechanicProfileRepository.findById(command.mechanicProfileId())
                .orElseThrow(() -> new IllegalArgumentException(
                        String.format("MechanicProfile %d not found", command.mechanicProfileId())));

        var specialty = specialtyRepository.findById(command.specialtyId())
                .orElseThrow(() -> new IllegalArgumentException(
                        String.format("Specialty %d not found", command.specialtyId())));

        profile.addSpecialty(specialty);
        return Optional.of(mechanicProfileRepository.save(profile));
    }

    /** Removes a Specialty from a MechanicProfile. */
    @Override
    @Transactional
    public Optional<MechanicProfile> handle(RemoveSpecialtyFromMechanicCommand command) {
        var profile = mechanicProfileRepository.findById(command.mechanicProfileId())
                .orElseThrow(() -> new IllegalArgumentException(
                        String.format("MechanicProfile %d not found", command.mechanicProfileId())));

        var specialty = specialtyRepository.findById(command.specialtyId())
                .orElseThrow(() -> new IllegalArgumentException(
                        String.format("Specialty %d not found", command.specialtyId())));

        profile.removeSpecialty(specialty);
        return Optional.of(mechanicProfileRepository.save(profile));
    }
}