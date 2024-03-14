package com.strong.BloodDonation.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.strong.BloodDonation.Model.LoginRequest;
import com.strong.BloodDonation.Security.JwtUtil;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    private JwtUtil jwtUtil;

    @PostMapping("/authenticate")
    public String createAuthenticationToken(@RequestBody LoginRequest loginRequest)
            throws UsernameNotFoundException {

                System.out.println("Hello ");
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getEmail(),
                        loginRequest.getPassword()));
        if (authentication.isAuthenticated()) {
            return jwtUtil.generateJwtToken(authentication.getPrincipal().toString(),
                    authentication.getAuthorities().toString());
        } else
            throw new UsernameNotFoundException("Invalid User Request");
    }

}