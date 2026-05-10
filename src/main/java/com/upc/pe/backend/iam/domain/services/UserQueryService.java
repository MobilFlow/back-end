package com.upc.pe.backend.iam.domain.services;


import com.upc.pe.backend.iam.domain.model.aggregates.User;
import com.upc.pe.backend.iam.domain.model.queries.GetAllUsersQuery;
import com.upc.pe.backend.iam.domain.model.queries.GetUserByIdQuery;

import java.util.List;
import java.util.Optional;

/**
 * Query service interface for user management within the IAM module.
 * Defines operations related to retrieving user information.
 */
public interface UserQueryService {
    /**
     * Handles the query to retrieve all users.
     * @param query The {@link GetAllUsersQuery}.
     * @return A list of all {@link User} entities.
     */
    List<User> handle(GetAllUsersQuery query);

    /**
     * Handles the query to retrieve a user by their ID.
     * @param query The {@link GetUserByIdQuery} containing the user's ID.
     * @return An {@link Optional} containing the {@link User} entity if found, or empty if not.
     */
    Optional<User> handle(GetUserByIdQuery query);

}
