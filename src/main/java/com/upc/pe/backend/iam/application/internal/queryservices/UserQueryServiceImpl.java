package com.upc.pe.backend.iam.application.internal.queryservices;


import com.upc.pe.backend.iam.domain.model.aggregates.User;
import com.upc.pe.backend.iam.domain.model.queries.GetAllUsersQuery;
import com.upc.pe.backend.iam.domain.model.queries.GetUserByIdQuery;
import com.upc.pe.backend.iam.domain.services.UserQueryService;
import com.upc.pe.backend.iam.infrastructure.persistence.jpa.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserQueryServiceImpl implements UserQueryService {
    private final UserRepository userRepository;

    public UserQueryServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    /**
     * Retrieves all users from the repository.
     *
     * @param query query object (not used in this case)
     * @return list of all users
     */
    @Override
    public List<User> handle(GetAllUsersQuery query) {
        return userRepository.findAll();
    }
    /**
     * Retrieves a user by their ID.
     *
     * @param query query containing the user ID
     * @return optional containing the user if found
     */
    @Override
    public Optional<User> handle(GetUserByIdQuery query) {
        return userRepository.findById(query.userId());
    }

}