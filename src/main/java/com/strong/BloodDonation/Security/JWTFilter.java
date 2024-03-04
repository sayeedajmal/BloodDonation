package com.strong.BloodDonation.Security;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Filter class responsible for JWT token authentication.
 */
@Component
public class JWTFilter extends OncePerRequestFilter {

    @Value("${bloodDonation.organisation.name}")
    private String OrganisationName;

    @Value("${jwt.secret}")
    private String SecretKey;

    @Value("${jwt.tokenValidityInSeconds}")
    private int ValidityInSeconds;

    @Override
    protected void doFilterInternal(@SuppressWarnings("null") HttpServletRequest request,
            @SuppressWarnings("null") HttpServletResponse response,
            @SuppressWarnings("null") FilterChain filterChain) throws ServletException, IOException {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null) {

            SecretKey key = Keys.hmacShaKeyFor(SecretKey.getBytes(StandardCharsets.UTF_8));
            String jwt = Jwts.builder()
                    .issuer(OrganisationName)
                    .subject("JWT_Token")
                    .claim("username", authentication.getName())
                    .claim("position", PopulatePositions(authentication.getAuthorities()))
                    .issuedAt(new Date())
                    .expiration(new Date(new Date().getTime() + ValidityInSeconds * 1000))
                    .signWith(key)
                    .compact();
            response.setHeader("Authorization", jwt);
        }

        filterChain.doFilter(request, response);
    }

    private Object PopulatePositions(Collection<? extends GrantedAuthority> authorities) {
        Set<String> positionSet = new HashSet<>();
        for (GrantedAuthority position : authorities) {
            positionSet.add(position.getAuthority());
        }
        return positionSet;
    }

    @Override
    protected boolean shouldNotFilter(@SuppressWarnings("null") HttpServletRequest request) throws ServletException {
        return !request.getServletPath().equals("/staff/createStaff");
    }

}