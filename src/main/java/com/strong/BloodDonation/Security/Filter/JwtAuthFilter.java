package com.strong.BloodDonation.Security.Filter;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.strong.BloodDonation.Model.Staff;
import com.strong.BloodDonation.Security.JwtUtil;
import com.strong.BloodDonation.Service.StaffService;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JwtAuthFilter extends OncePerRequestFilter {

    @Autowired
    final JwtUtil jwtUtil;

    @Autowired
    final StaffService staffService;

    public JwtAuthFilter(JwtUtil jwtUtil, StaffService staffService) {
        this.jwtUtil = jwtUtil;
        this.staffService = staffService;
    }

    @SuppressWarnings("null")
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        String AuthHeader = jwtUtil.extractJwtToken(request);

        String token = AuthHeader.substring(7);
        String email = jwtUtil.extractEmailFromJwtToken(token);

        if (email != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            Staff staff = staffService.findByEmail(email);
            if (jwtUtil.validateJwtToken(token)) {
                UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(staff, null,
                        jwtUtil.getAuthorityList(staff));

                authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

                SecurityContextHolder.getContext().setAuthentication(authToken);
            }
        }
        filterChain.doFilter(request, response);
    }

    @SuppressWarnings("null")
    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) {
        return !request.getServletPath().equals("/auth/authenticate");
    }

}
