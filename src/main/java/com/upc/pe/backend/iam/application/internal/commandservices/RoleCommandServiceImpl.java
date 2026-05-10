package com.upc.pe.backend.iam.application.internal.commandservices;

import com.upc.pe.backend.iam.domain.model.commands.SeedRolesCommand;
import com.upc.pe.backend.iam.domain.model.entities.Role;
import com.upc.pe.backend.iam.domain.model.valueobjects.Roles;
import com.upc.pe.backend.iam.domain.services.RoleCommandService;
import com.upc.pe.backend.iam.infrastructure.persistence.jpa.repositories.RoleRepository;
import org.springframework.stereotype.Service;

import java.util.Arrays;

/**
 * Implementation of RoleCommandService.
 * This service is responsible for handling role-related commands,
 * specifically seeding the system with default roles if they are not already present.
 *
 */
@Service
public class RoleCommandServiceImpl implements RoleCommandService {
    private final RoleRepository roleRepository;

    /**
     * Creates an instance of RoleCommandServiceImpl with the given RoleRepository.
     *
     * @param roleRepository the repository for managing Role entities
     */
    public RoleCommandServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    /**
     * Seeds the default roles defined in the Roles enum if they are not already present.
     *
     * @param command the command containing the instruction to seed roles
     */
    @Override
    public void handle(SeedRolesCommand command) {
        Arrays.stream(Roles.values()).forEach(role -> {
            if (!roleRepository.existsByName(role)) {
                roleRepository.save(new Role(role));
            }
        });
    }
}