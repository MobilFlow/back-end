package com.upc.pe.backend.iam.infrastructure.tokens.jwt.services;

import com.upc.pe.backend.iam.domain.model.entities.Role;
import com.upc.pe.backend.iam.infrastructure.tokens.jwt.BearerTokenService;
import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * Token service implementation for JWT tokens.
 * This class is responsible for generating and validating JWT tokens.
 * It uses the secret and expiration days from the application.properties file.
 */
@Service
public class TokenServiceImpl implements BearerTokenService {

    private final static Logger LOGGER = LoggerFactory.getLogger(TokenServiceImpl.class);

    @Value("${authorization.jwt.secret}")
    private String secret;

    @Value("${authorization.jwt.expiration.days}")
    private int expirationDays;

    /**
     * Get signing key from secret.
     * @return {@link SecretKey} signing key.
     */
    private SecretKey getSigningKey() {
        byte[] keyBytes = Decoders.BASE64.decode(secret);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    /**
     * Get all claims from token.
     * @param token The token.
     * @return {@link Claims} all claims from token.
     */
    private Claims extractAllClaims(String token) {
        return Jwts.parser()
                .verifyWith(getSigningKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

    /**
     * Extract a single claim from the token using a claims resolver function.
     * @param token The token.
     * @param claimsResolver The claims resolver function.
     * @return {@code T} a single claim from the token.
     */
    private <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    /**
     * Extract username from token.
     * @param token The token.
     * @return {@link String} username from token.
     */
    @Override
    public String getUsernameFromToken(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    /**
     * Generate token for a given username.
     * @param username The username.
     * @return {@link String} generated token.
     */
    @Override
    public String generateToken(String username) {
        return generateToken(username, new HashMap<>());
    }

    /**
     * Generate token for a given username with roles.
     * @param username The username.
     * @param roles The set of roles.
     * @return {@link String} generated token.
     */
    @Override
    public String generateToken(String username, Set<Role> roles) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("roles", roles.stream().map(role -> role.getName().name()).collect(Collectors.toList()));
        return generateToken(username, claims);
    }


    /**
     * Generate token for a given username with extra claims.
     * @param username The username.
     * @param extraClaims The extra claims.
     * @return {@link String} generated token.
     */
    private String generateToken(String username, Map<String, Object> extraClaims) {

        Date now = new Date(System.currentTimeMillis());
        Date expiration = new Date(now.getTime() + expirationDays * 1000L * 60 * 60 * 24);

        return Jwts.builder()
                .claims(extraClaims)
                .subject(username)
                .issuedAt(now)
                .expiration(expiration)
                .signWith(getSigningKey(), Jwts.SIG.HS256)
                .compact();
    }


    /**
     * Validate token.
     * @param token The token.
     * @return {@code true} if token is valid, {@code false} otherwise.
     */
    @Override
    public boolean validateToken(String token) {
        try {
            Jwts.parser()
                    .verifyWith(getSigningKey())
                    .build()
                    .parseSignedClaims(token);
            return true;
        } catch (MalformedJwtException e) {
            LOGGER.error("Invalid JWT token: {}", e.getMessage());
        } catch (ExpiredJwtException e) {
            LOGGER.error("JWT token is expired: {}", e.getMessage());
        } catch (UnsupportedJwtException e) {
            LOGGER.error("JWT token is unsupported: {}", e.getMessage());
        } catch (IllegalArgumentException e) {
            LOGGER.error("JWT claims string is empty: {}", e.getMessage());
        }
        return false;
    }
}