package com.upc.pe.backend.iam.domain.model.entities;

import com.upc.pe.backend.iam.domain.model.valueobjects.Roles;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.With;

import java.util.List;

/**
 * Role entity for the IAM module.
 * <p>
 * This entity defines the roles a user can have within the system,
 * which are used for authorization and access control.
 * </p>
 */
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@With
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(length = 20, nullable = false, unique = true)
    private Roles name;

    /**
     * Constructor to create a Role with a specified name.
     * @param name The enum value representing the role's name.
     */
    public Role(Roles name) {
        this.name = name;
    }

    /**
     * Retrieves the string representation of the role's name.
     * @return The role name as a String.
     */
    public String getStringName() {
        return name.name();
    }

    /**
     * Provides a default role (ROLE_DRIVER) for new user registrations.
     * @return A new Role instance representing the default user role.
     */
    public static Role getDefaultRole() {
        return new Role(Roles.ROLE_DRIVER);
    }


    public static Role toRoleFromName(String name) {
        return new Role(Roles.valueOf(name));
    }

    /**
     * Validates a list of roles. If the list is null or empty, it returns a list containing only the default role (ROLE_USER).
     * @param roles The list of roles to validate.
     * @return A validated list of roles, or a list with the default role if the input was invalid.
     */
    public static List<Role> validateRoleSet(List<Role> roles) {
        if (roles == null || roles.isEmpty()) {
            return List.of(getDefaultRole());
        }
        return roles;
    }
}