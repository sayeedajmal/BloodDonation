package com.strong.BloodDonation.Security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {

    /* Creating A Bean For Enable Security */
    @Bean
    public SecurityFilterChain EnableWebSecurity(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.csrf(csrf -> csrf.disable())
                .cors(cors -> cors.disable())
                .sessionManagement(management -> management
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .formLogin(formLogin -> formLogin.disable())
                .securityMatcher("/**")
                .authorizeHttpRequests((authz) -> authz
                        .requestMatchers("/").authenticated()
                        .requestMatchers("/auth/login").permitAll()
                        .anyRequest().authenticated());
        return httpSecurity.build();
    }
}
