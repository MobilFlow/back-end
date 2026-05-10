package com.upc.pe.backend.iam.domain.model.aggregates;

import com.upc.pe.backend.iam.domain.model.entities.Role;
import com.upc.pe.backend.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * User Aggregate Root for IAM.
 *
 * This entity represents the core user identity within the platform,
 * combining personal details with authentication and authorization capabilities.
 * It is an aggregate root responsible for managing its own lifecycle and relationships,
 * including roles and associated subscriptions.
 *
 * @see AuditableAbstractAggregateRoot
 */
@Entity
@Getter
@Setter
@Table(name = "users")
@EntityListeners(AuditingEntityListener.class)
public class User extends AuditableAbstractAggregateRoot<User> {

    @NotBlank
    @Size(max = 50)
    @Column(unique = true, nullable = false)
    private String email;

    @NotBlank
    @Size(max = 120)
    @Column(nullable = false)
    private String password;

    @Size(max = 100)
    @Column(nullable = false)
    private String fullName;

    @NotBlank
    @Size(max = 20)
    @Column(nullable = false)
    private String phoneNumber;

    @Column
    private String profilePicture;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(
            name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private Set<Role> roles;

    protected User() {
        this.roles = new HashSet<>();
    }


    public User(String email, String password, String fullName, String phoneNumber, String profilePicture) {
        this();
        this.email = email;
        this.password = password;
        this.fullName = fullName;
        this.phoneNumber = phoneNumber;
        this.profilePicture = profilePicture;
    }

    public User(String email, String password, String fullName, String phoneNumber,
                String profilePicture, List<Role> roles) {
        this(email, password, fullName, phoneNumber, profilePicture);
        addRoles(roles);
    }

    /**
     * Adds a single role to the user.
     *
     * @param role the Role entity to add
     * @return this User instance for method chaining
     */
    public User addRole(Role role) {
        this.roles.add(role);
        return this;
    }

    /**
     * Adds a collection of roles to the user.
     *
     * @param roles list of Role entities to add
     * @return this User instance for method chaining
     */
    public User addRoles(List<Role> roles) {
        var validated = Role.validateRoleSet(roles);
        this.roles.addAll(validated);
        return this;
    }
}