package com.upc.pe.backend.iam.application.internal.queryservices;


import com.upc.pe.backend.iam.domain.model.entities.Role;
import com.upc.pe.backend.iam.domain.model.queries.GetAllRolesQuery;
import com.upc.pe.backend.iam.domain.model.queries.GetRoleByNameQuery;
import com.upc.pe.backend.iam.domain.services.RoleQueryService;
import com.upc.pe.backend.iam.infrastructure.persistence.jpa.repositories.RoleRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Service that handles role-related queries.
 */
@Service
public class RoleQueryServiceImpl implements RoleQueryService {
    private final RoleRepository roleRepository;

    public RoleQueryServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }
    /**
     * Retrieves all roles from the repository.
     *
     * @param query query object (not used in this case)
     * @return list of all roles
     */
    @Override
    public List<Role> handle(GetAllRolesQuery query) {
        return roleRepository.findAll();
    }
    /**
     * Retrieves a role by its name.
     *
     * @param query query containing the role name
     * @return optional containing the role if found
     */
    @Override
    public Optional<Role> handle(GetRoleByNameQuery query) {
        return roleRepository.findByName(query.roleName());
    }
}