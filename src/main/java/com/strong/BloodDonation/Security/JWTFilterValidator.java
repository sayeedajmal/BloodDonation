package com.strong.BloodDonation.Security;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JWTFilterValidator extends OncePerRequestFilter {

    @Value("${jwt.secret}")
    private String SecretKey;

    @SuppressWarnings("null")
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        String jwt = request.getHeader("Authorization");
        if (jwt != null) {
            try {
                SecretKey key = Keys.hmacShaKeyFor(SecretKey.getBytes(StandardCharsets.UTF_8));
                Claims claims = Jwts.parser().verifyWith(key).build().parseSignedClaims(jwt).getPayload();
                String username = String.valueOf(claims.get("username"));
                String position = (String) claims.get("position");


                Authentication authentication = new UsernamePasswordAuthenticationToken(username, null,
                        AuthorityUtils.commaSeparatedStringToAuthorityList(position));
                SecurityContextHolder.getContext().setAuthentication(authentication);
            } catch (Exception e) {
                throw new BadCredentialsException(e.getMessage());
            }
            filterChain.doFilter(request, response);
        }
    }

}
