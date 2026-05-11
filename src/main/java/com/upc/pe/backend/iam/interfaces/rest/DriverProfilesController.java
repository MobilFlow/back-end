package com.upc.pe.backend.iam.interfaces.rest;

import com.upc.pe.backend.iam.domain.model.commands.DeleteCarCommand;
import com.upc.pe.backend.iam.domain.model.queries.GetCarsByDriverProfileIdQuery;
import com.upc.pe.backend.iam.domain.model.queries.GetDriverProfileByUserIdQuery;
import com.upc.pe.backend.iam.domain.services.DriverProfileCommandService;
import com.upc.pe.backend.iam.domain.services.DriverProfileQueryService;
import com.upc.pe.backend.iam.interfaces.rest.resources.*;
import com.upc.pe.backend.iam.interfaces.rest.transform.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.stream.Collectors;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping(value = "/api/v1/drivers", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "Drivers", description = "Available Driver Endpoints")
public class DriverProfilesController {

    private final DriverProfileCommandService commandService;
    private final DriverProfileQueryService queryService;

    public DriverProfilesController(
            DriverProfileCommandService commandService,
            DriverProfileQueryService queryService
    ) {
        this.commandService = commandService;
        this.queryService = queryService;
    }

    @PostMapping
    @Operation(summary = "Create driver profile")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Driver profile created"),
            @ApiResponse(responseCode = "400", description = "Invalid request")
    })
    public ResponseEntity<DriverProfileResource> createDriverProfile(
            @RequestBody CreateDriverProfileResource resource
    ) {

        var command = CreateDriverProfileCommandFromResourceAssembler
                .toCommandFromResource(resource);

        var profile = commandService.handle(command);

        if (profile.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }

        var response = DriverProfileResourceFromEntityAssembler
                .toResourceFromEntity(profile.get());

        return ResponseEntity.ok(response);
    }


    @GetMapping("/user/{userId}")
    @Operation(summary = "Get driver profile by user id")
    public ResponseEntity<DriverProfileResource> getDriverProfileByUserId(
            @PathVariable Long userId
    ) {

        var query = new GetDriverProfileByUserIdQuery(userId);

        var profile = queryService.handle(query);

        if (profile.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        var response = DriverProfileResourceFromEntityAssembler
                .toResourceFromEntity(profile.get());

        return ResponseEntity.ok(response);
    }

    @PostMapping("/{driverProfileId}/cars")
    @Operation(summary = "Register car")
    public ResponseEntity<CarResource> registerCar(
            @PathVariable Long driverProfileId,
            @RequestBody RegisterCarResource resource
    ) {

        var command = RegisterCarCommandFromResourceAssembler
                .toCommandFromResource(driverProfileId, resource);

        var car = commandService.handle(command);

        if (car.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }

        var response = CarResourceFromEntityAssembler
                .toResourceFromEntity(car.get());

        return ResponseEntity.ok(response);
    }

    @PutMapping("/cars/{carId}")
    @Operation(summary = "Update car")
    public ResponseEntity<CarResource> updateCar(
            @PathVariable Long carId,
            @RequestBody UpdateCarResource resource
    ) {

        var command = UpdateCarCommandFromResourceAssembler
                .toCommandFromResource(carId, resource);

        var car = commandService.handle(command);

        if (car.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        var response = CarResourceFromEntityAssembler
                .toResourceFromEntity(car.get());

        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/cars/{carId}")
    @Operation(summary = "Delete car")
    public ResponseEntity<?> deleteCar(@PathVariable Long carId) {

        var command = new DeleteCarCommand(carId);

        commandService.handle(command);

        return ResponseEntity.ok().build();
    }

    @GetMapping("/{driverProfileId}/cars")
    @Operation(summary = "Get cars by driver profile id")
    public ResponseEntity<?> getCarsByDriverProfileId(
            @PathVariable Long driverProfileId
    ) {

        var query = new GetCarsByDriverProfileIdQuery(driverProfileId);

        var cars = queryService.handle(query);

        var resources = cars.stream()
                .map(CarResourceFromEntityAssembler::toResourceFromEntity)
                .collect(Collectors.toList());

        return ResponseEntity.ok(resources);
    }
}