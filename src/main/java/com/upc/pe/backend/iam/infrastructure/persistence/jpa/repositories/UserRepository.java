package com.upc.pe.backend.iam.infrastructure.persistence.jpa.repositories;

import com.upc.pe.backend.iam.domain.model.aggregates.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Repository interface for User entity operations within the IAM module.
 * It extends JpaRepository to provide standard CRUD operations.
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    /*** Finds all users.
     * @return A list of all User entities.
     */
    List<User> findAll();

    /**
     * Finds a User by their email address.
     * In this system, the email acts as the unique identifier (username) for login.
     * @param email The email address of the user.
     * @return An Optional containing the User if found, or empty otherwise.
     */
    Optional<User> findByEmail(String email);

    /**
     * Checks if a user exists by their email address.
     * @param email The email address to check.
     * @return True if a user with the given email exists, false otherwise.
     */
    boolean existsByEmail(String email);

}