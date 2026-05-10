package com.upc.pe.backend.iam.interfaces.rest;


import com.upc.pe.backend.iam.domain.model.commands.ChangePasswordCommand;
import com.upc.pe.backend.iam.domain.model.commands.UpdateUserCommand;
import com.upc.pe.backend.iam.domain.model.queries.GetAllUsersQuery;
import com.upc.pe.backend.iam.domain.model.queries.GetUserByIdQuery;
import com.upc.pe.backend.iam.domain.services.UserCommandService;
import com.upc.pe.backend.iam.domain.services.UserQueryService;
import com.upc.pe.backend.iam.interfaces.rest.resources.ChangePasswordResource;
import com.upc.pe.backend.iam.interfaces.rest.resources.UpdateUserResource;
import com.upc.pe.backend.iam.interfaces.rest.resources.UserResource;
import com.upc.pe.backend.iam.interfaces.rest.transform.UserResourceFromEntityAssembler;
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
 * This class is a REST controller that exposes the users resource.
 * It includes the following operations:
 * - GET /api/v1/users: returns all the users
 * - GET /api/v1/users/{userId}: returns the user with the given id
 **/
@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping(value = "/api/v1/users", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "Users", description = "Available User Endpoints")
public class UsersController {
    private final UserQueryService userQueryService;
    private final UserCommandService userCommandService;

    public UsersController(UserQueryService userQueryService, UserCommandService userCommandService) {
        this.userQueryService = userQueryService;
        this.userCommandService = userCommandService;
    }

    /**
     * This method returns all the users.
     * @return a list of user resources
     * @see UserResource
     */
    @GetMapping
    @Operation(summary = "Get all users", description = "Get all the users available in the system.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Users retrieved successfully."),
            @ApiResponse(responseCode = "401", description = "Unauthorized.")})
    public ResponseEntity<List<UserResource>> getAllUsers() {
        var getAllUsersQuery = new GetAllUsersQuery();
        var users = userQueryService.handle(getAllUsersQuery);
        var userResources = users.stream().map(UserResourceFromEntityAssembler::toResourceFromEntity).collect(Collectors.toList());
        return ResponseEntity.ok(userResources);
    }

    /**
     * This method returns the user with the given id.
     * @param userId the user id
     * @return the user resource with the given id
     * @throws RuntimeException if the user is not found
     * @see UserResource
     */
    @GetMapping(value = "/{userId}")
    @Operation(summary = "Get user by id", description = "Get the user with the given id.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "User retrieved successfully."),
            @ApiResponse(responseCode = "404", description = "User not found."),
            @ApiResponse(responseCode = "401", description = "Unauthorized.")})
    public ResponseEntity<UserResource> getUserById(@PathVariable Long userId) {
        var getUserByIdQuery = new GetUserByIdQuery(userId);
        var user = userQueryService.handle(getUserByIdQuery);
        if (user.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        var userResource = UserResourceFromEntityAssembler.toResourceFromEntity(user.get());
        return ResponseEntity.ok(userResource);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserResource> updateUser(@PathVariable Long id, @RequestBody UpdateUserResource resource) {
        var command = new UpdateUserCommand(id, resource.name(), resource.email(), resource.profilePicture());
        var updatedUser = userCommandService.updateUser(command);
        var userResource = UserResourceFromEntityAssembler.toResourceFromEntity(updatedUser);
        return ResponseEntity.ok(userResource);
    }

    @PatchMapping("/{id}/change-password")
    public ResponseEntity<?> changePassword(@PathVariable Long id, @RequestBody ChangePasswordResource resource) {
        var command = new ChangePasswordCommand(id, resource.currentPassword(), resource.newPassword());

        try {
            var result = userCommandService.changePassword(command);
            if (result.isEmpty()) return ResponseEntity.notFound().build();
            return ResponseEntity.ok().build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }





}