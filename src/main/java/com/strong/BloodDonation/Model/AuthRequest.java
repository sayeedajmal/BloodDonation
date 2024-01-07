package com.strong.BloodDonation.Model;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class AuthRequest {
    private String email;
    private String password;
}
