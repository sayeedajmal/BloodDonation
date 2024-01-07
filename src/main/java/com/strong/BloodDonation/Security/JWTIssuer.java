package com.strong.BloodDonation.Security;

import java.time.Duration;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.List;

import org.springframework.stereotype.Component;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class JWTIssuer {

    private final JWTProperties properties;

    public String Issue(long userId, String Email, List<String> roles) {
        return JWT.create().withSubject(String.valueOf(userId))
                .withExpiresAt(Instant.now().plus(Duration.of(1, ChronoUnit.DAYS)))
                .withClaim("e", Email)
                .withClaim("r", roles)
                .sign(Algorithm.HMAC256(properties.getSecretKey()));
    }
}
