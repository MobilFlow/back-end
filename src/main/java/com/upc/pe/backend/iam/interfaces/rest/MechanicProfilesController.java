package com.upc.pe.backend.iam.interfaces.rest;

import com.upc.pe.backend.iam.domain.model.commands.AddSpecialtyToMechanicCommand;
import com.upc.pe.backend.iam.domain.model.commands.RemoveSpecialtyFromMechanicCommand;
import com.upc.pe.backend.iam.domain.model.queries.GetAllMechanicProfilesQuery;
import com.upc.pe.backend.iam.domain.model.queries.GetAllSpecialtiesQuery;
import com.upc.pe.backend.iam.domain.model.queries.GetMechanicProfileByUserIdQuery;
import com.upc.pe.backend.iam.domain.services.MechanicProfileCommandService;
import com.upc.pe.backend.iam.domain.services.MechanicProfileQueryService;
import com.upc.pe.backend.iam.interfaces.rest.resources.CreateMechanicProfileResource;
import com.upc.pe.backend.iam.interfaces.rest.resources.MechanicProfileResource;
import com.upc.pe.backend.iam.interfaces.rest.resources.UpdateMechanicProfileResource;
import com.upc.pe.backend.iam.interfaces.rest.transform.CreateMechanicProfileCommandFromResourceAssembler;
import com.upc.pe.backend.iam.interfaces.rest.transform.MechanicProfileResourceFromEntityAssembler;
import com.upc.pe.backend.iam.interfaces.rest.transform.SpecialtyResourceFromEntityAssembler;
import com.upc.pe.backend.iam.interfaces.rest.transform.UpdateMechanicProfileCommandFromResourceAssembler;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.stream.Collectors;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping(value = "/api/v1/mechanics", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "Mechanics", description = "Available Mechanic Endpoints")
public class MechanicProfilesController {

    private final MechanicProfileCommandService commandService;
    private final MechanicProfileQueryService queryService;

    public MechanicProfilesController(
            MechanicProfileCommandService commandService,
            MechanicProfileQueryService queryService
    ) {
        this.commandService = commandService;
        this.queryService = queryService;
    }

    @PostMapping
    @Operation(summary = "Create mechanic profile")
    public ResponseEntity<MechanicProfileResource> createMechanicProfile(
            @RequestBody CreateMechanicProfileResource resource
    ) {

        var command = CreateMechanicProfileCommandFromResourceAssembler
                .toCommandFromResource(resource);

        var profile = commandService.handle(command);

        if (profile.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }

        var response = MechanicProfileResourceFromEntityAssembler
                .toResourceFromEntity(profile.get());

        return ResponseEntity.ok(response);
    }

    @PutMapping("/{mechanicProfileId}")
    @Operation(summary = "Update mechanic profile")
    public ResponseEntity<MechanicProfileResource> updateMechanicProfile(
            @PathVariable Long mechanicProfileId,
            @RequestBody UpdateMechanicProfileResource resource
    ) {

        var command = UpdateMechanicProfileCommandFromResourceAssembler
                .toCommandFromResource(mechanicProfileId, resource);

        var profile = commandService.handle(command);

        if (profile.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        var response = MechanicProfileResourceFromEntityAssembler
                .toResourceFromEntity(profile.get());

        return ResponseEntity.ok(response);
    }

    @GetMapping
    @Operation(summary = "Get all mechanic profiles")
    public ResponseEntity<?> getAllMechanicProfiles() {

        var query = new GetAllMechanicProfilesQuery();

        var profiles = queryService.handle(query);

        var resources = profiles.stream()
                .map(MechanicProfileResourceFromEntityAssembler::toResourceFromEntity)
                .collect(Collectors.toList());

        return ResponseEntity.ok(resources);
    }

    @GetMapping("/user/{userId}")
    @Operation(summary = "Get mechanic profile by user id")
    public ResponseEntity<MechanicProfileResource> getMechanicProfileByUserId(
            @PathVariable Long userId
    ) {

        var query = new GetMechanicProfileByUserIdQuery(userId);

        var profile = queryService.handle(query);

        if (profile.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        var response = MechanicProfileResourceFromEntityAssembler
                .toResourceFromEntity(profile.get());

        return ResponseEntity.ok(response);
    }

    @GetMapping("/specialties")
    @Operation(summary = "Get all specialties")
    public ResponseEntity<?> getAllSpecialties() {

        var query = new GetAllSpecialtiesQuery();

        var specialties = queryService.handle(query);

        var resources = specialties.stream()
                .map(SpecialtyResourceFromEntityAssembler::toResourceFromEntity)
                .collect(Collectors.toList());

        return ResponseEntity.ok(resources);
    }

    @PostMapping("/{mechanicProfileId}/specialties/{specialtyId}")
    @Operation(summary = "Add specialty to mechanic")
    public ResponseEntity<MechanicProfileResource> addSpecialty(
            @PathVariable Long mechanicProfileId,
            @PathVariable Long specialtyId
    ) {

        var command = new AddSpecialtyToMechanicCommand(
                mechanicProfileId,
                specialtyId
        );

        var profile = commandService.handle(command);

        if (profile.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        var response = MechanicProfileResourceFromEntityAssembler
                .toResourceFromEntity(profile.get());

        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{mechanicProfileId}/specialties/{specialtyId}")
    @Operation(summary = "Remove specialty from mechanic")
    public ResponseEntity<MechanicProfileResource> removeSpecialty(
            @PathVariable Long mechanicProfileId,
            @PathVariable Long specialtyId
    ) {

        var command = new RemoveSpecialtyFromMechanicCommand(
                mechanicProfileId,
                specialtyId
        );

        var profile = commandService.handle(command);

        if (profile.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        var response = MechanicProfileResourceFromEntityAssembler
                .toResourceFromEntity(profile.get());

        return ResponseEntity.ok(response);
    }
}