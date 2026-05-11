package com.upc.pe.backend.iam.infrastructure.tokens.jwt;


import com.upc.pe.backend.iam.domain.model.entities.Role;

public interface BearerTokenService {
    String generateToken(String username);
    String generateToken(String username, Role role);
    String getUsernameFromToken(String token);
    boolean validateToken(String token);
}