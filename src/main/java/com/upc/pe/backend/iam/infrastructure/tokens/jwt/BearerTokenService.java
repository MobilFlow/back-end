package com.upc.pe.backend.iam.infrastructure.tokens.jwt;


import com.upc.pe.backend.iam.domain.model.entities.Role;

import java.util.Set;

public interface BearerTokenService {
    String generateToken(String username);
    String generateToken(String username, Set<Role> roles);
    String getUsernameFromToken(String token);
    boolean validateToken(String token);
}