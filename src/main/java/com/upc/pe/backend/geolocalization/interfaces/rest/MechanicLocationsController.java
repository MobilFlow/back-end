package com.upc.pe.backend.geolocalization.interfaces.rest;

import com.upc.pe.backend.geolocalization.domain.model.commands.RegisterMechanicLocationCommand;
import com.upc.pe.backend.geolocalization.domain.model.queries.GetAllMechanicLocationsQuery;
import com.upc.pe.backend.geolocalization.domain.model.queries.GetMechanicLocationQuery;
import com.upc.pe.backend.geolocalization.domain.services.MechanicLocationCommandService;
import com.upc.pe.backend.geolocalization.domain.services.MechanicLocationQueryService;
import com.upc.pe.backend.geolocalization.interfaces.rest.resources.MechanicLocationResource;
import com.upc.pe.backend.geolocalization.interfaces.rest.resources.RegisterMechanicLocationResource;
import com.upc.pe.backend.geolocalization.interfaces.rest.transform.MechanicLocationResourceFromEntityAssembler;
import com.upc.pe.backend.geolocalization.interfaces.rest.transform.RegisterMechanicLocationCommandFromResourceAssembler;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

/**
 * REST controller for mechanic geolocation management.
 */
@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping(
        value = "/api/v1/mechanic-locations",
        produces = MediaType.APPLICATION_JSON_VALUE
)
@Tag(
        name = "Mechanic Locations",
        description = "Available Mechanic Location Endpoints"
)
public class MechanicLocationsController {

    private final MechanicLocationCommandService mechanicLocationCommandService;
    private final MechanicLocationQueryService mechanicLocationQueryService;

    public MechanicLocationsController(
            MechanicLocationCommandService mechanicLocationCommandService,
            MechanicLocationQueryService mechanicLocationQueryService
    ) {
        this.mechanicLocationCommandService = mechanicLocationCommandService;
        this.mechanicLocationQueryService = mechanicLocationQueryService;
    }

    /**
     * Returns all mechanic locations.
     *
     * @return list of mechanic location resources
     */
    @GetMapping
    @Operation(
            summary = "Get all mechanic locations",
            description = "Returns all registered mechanic locations."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Locations retrieved successfully."),
            @ApiResponse(responseCode = "401", description = "Unauthorized.")
    })
    public ResponseEntity<List<MechanicLocationResource>> getAllMechanicLocations() {

        var query = new GetAllMechanicLocationsQuery();

        var locations = mechanicLocationQueryService.handle(query);

        var resources = locations.stream()
                .map(MechanicLocationResourceFromEntityAssembler::toResourceFromEntity)
                .collect(Collectors.toList());

        return ResponseEntity.ok(resources);
    }

    /**
     * Returns mechanic location by mechanic id.
     *
     * @param mechanicId mechanic id
     * @return mechanic location resource
     */
    @GetMapping("/{mechanicId}")
    @Operation(
            summary = "Get mechanic location",
            description = "Returns the location of the specified mechanic."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Location retrieved successfully."),
            @ApiResponse(responseCode = "404", description = "Location not found."),
            @ApiResponse(responseCode = "401", description = "Unauthorized.")
    })
    public ResponseEntity<MechanicLocationResource> getMechanicLocationByMechanicId(
            @PathVariable Long mechanicId
    ) {

        var query = new GetMechanicLocationQuery(mechanicId);

        var location = mechanicLocationQueryService.handle(query);

        if (location.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        var resource =
                MechanicLocationResourceFromEntityAssembler
                        .toResourceFromEntity(location.get());

        return ResponseEntity.ok(resource);
    }

    /**
     * Registers or updates a mechanic location.
     *
     * @param mechanicId mechanic id
     * @param resource   location payload
     * @return created or updated location
     */
    @PostMapping("/{mechanicId}")
    @Operation(
            summary = "Register mechanic location",
            description = "Registers or updates a mechanic's current location."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Location registered successfully."),
            @ApiResponse(responseCode = "400", description = "Invalid request."),
            @ApiResponse(responseCode = "401", description = "Unauthorized.")
    })
    public ResponseEntity<MechanicLocationResource> registerMechanicLocation(
            @PathVariable Long mechanicId,
            @RequestBody RegisterMechanicLocationResource resource
    ) {

        var command =
                RegisterMechanicLocationCommandFromResourceAssembler
                        .toCommandFromResource(mechanicId, resource);

        var location =
                mechanicLocationCommandService.handle(command);

        if (location.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }

        var locationResource =
                MechanicLocationResourceFromEntityAssembler
                        .toResourceFromEntity(location.get());

        return ResponseEntity.ok(locationResource);
    }
}