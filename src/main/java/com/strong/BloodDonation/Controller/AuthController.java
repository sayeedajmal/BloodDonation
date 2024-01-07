package com.strong.BloodDonation.Controller;

import java.util.List;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.strong.BloodDonation.Model.AuthRequest;
import com.strong.BloodDonation.Model.AuthResponse;
import com.strong.BloodDonation.Security.JWTIssuer;

import io.swagger.v3.oas.annotations.parameters.RequestBody;
import lombok.RequiredArgsConstructor;
import lombok.var;

@RestController
@RequiredArgsConstructor
public class AuthController {

    private final JWTIssuer jwtIssuer;

    @PostMapping("/auth/login")
    public AuthResponse login(@RequestBody @Validated AuthRequest authRequest) {
        var token = jwtIssuer.Issue(1L, "test@test.com", List.of("USER"));
        return AuthResponse.builder().accessToken(token).build();
    }
}
