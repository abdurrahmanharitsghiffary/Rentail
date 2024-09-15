package com.goshier.kopos.service;

import io.jsonwebtoken.Claims;
import org.springframework.security.core.userdetails.UserDetails;

import java.security.Key;
import java.util.Date;
import java.util.Map;
import java.util.function.Function;

public interface JwtService {
    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver);

    public String generateToken(UserDetails userDetails);

    public String generateToken(Map<String, Object> extraClaims, UserDetails userDetails);

    private String buildToken(
            Map<String, Object> extraClaims,
            UserDetails userDetails,
            Long expiration
    ) {
        return null;
    }

    public long getExpirationTime();

    public String extractUsername(String token);

    public boolean isTokenValid(String token, UserDetails userDetails);

    private boolean isTokenExpired(String token) {
        return false;
    }

    private Date extractExpiration(String token) {
        return null;
    }

    private Claims extractAllClaims(String token) {
        return null;
    }

    private Key getSignInKey() {
        return null;
    }
}