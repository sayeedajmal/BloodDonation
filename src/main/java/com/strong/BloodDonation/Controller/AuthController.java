package com.strong.BloodDonation.Controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.strong.BloodDonation.Model.LoginRequest;
import com.strong.BloodDonation.Service.AuthService;

@RestController
public class AuthController {

        private final AuthService authService;

        public AuthController(AuthService authService) {
                this.authService = authService;
        }

        @PostMapping("/authenticate")
        public ResponseEntity<?> login() {
                return new ResponseEntity("hell", HttpStatus.OK);
        }

}