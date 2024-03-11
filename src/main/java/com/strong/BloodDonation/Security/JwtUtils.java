package com.strong.BloodDonation.Security;

import java.nio.charset.StandardCharsets;
import java.util.Collection;
import java.util.Date;

import javax.crypto.SecretKey;

import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.http.HttpServletRequest;

@Component
public class JwtUtils {

    private static String SecretKey = "fuckfuckfuckfuckfuckfuckfuckfuckfuckfuckfuckfuckfuckfuckfuckfuck";

    public  String generateJwtToken(String email, String Position) {
        return Jwts.builder()
                .issuer("BloodDonation")
                .subject("JWTToken")
                .claim("email", email)
                .claim("position", Position)
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 10)) // 10 Hour
                .signWith(getSecretKey())
                .compact();

    }

    public static String getEmailFromJwtToken(String token) {
        return Jwts.parser()
                .verifyWith(getSecretKey()).build()
                .parseSignedClaims(token)
                .getPayload()
                .getSubject();
    }

    private static SecretKey getSecretKey() {
        return Keys.hmacShaKeyFor(SecretKey.getBytes(StandardCharsets.UTF_8));
    }

    public static Claims validateJwtToken(String authToken) {
        try {
            return Jwts.parser().verifyWith(getSecretKey()).build().parseSignedClaims(authToken).getPayload();
        } catch (io.jsonwebtoken.security.SignatureException e) {
            System.out.println("JWT signature mismatch: {}" + e.getLocalizedMessage());
        } catch (io.jsonwebtoken.MalformedJwtException e) {
            System.out.println("JWT token is malformed: {}" + e.getLocalizedMessage());
        } catch (Exception e) {
            System.out.println("Error validating JWT token: {}" + e.getLocalizedMessage());
        }
        return null;
    }

    public static String extractJwtToken(HttpServletRequest request) {
        String authorizationHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            return authorizationHeader.substring(7);
        }
        return null;
    }

    public static Authentication getAuthenticationFromJwtToken(String jwtToken) {
        Claims claims = Jwts.parser().verifyWith(getSecretKey()).build().parseSignedClaims(jwtToken).getPayload();
        String username = claims.getSubject();
        String authorities = claims.get("position", String.class);
        Collection<GrantedAuthority> authorityList = AuthorityUtils.commaSeparatedStringToAuthorityList(authorities);

        return new UsernamePasswordAuthenticationToken(username, authorityList);
    }
}
