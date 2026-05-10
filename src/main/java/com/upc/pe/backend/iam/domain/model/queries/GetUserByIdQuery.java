package com.upc.pe.backend.iam.domain.model.queries;

/**
 * Query to retrieve a user by their unique identifier (ID).
 * This query is commonly used in the IAM module to retrieve specific user information.
 *
 * @param userId The ID of the user to search for.
 */
public record GetUserByIdQuery(Long userId) {
    public GetUserByIdQuery {
        if (userId == null) {
            throw new IllegalArgumentException("User ID cannot be null");
        }
    }
}