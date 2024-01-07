package com.strong.BloodDonation.Model;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class AuthResponse {
    private final String accessToken;
}
