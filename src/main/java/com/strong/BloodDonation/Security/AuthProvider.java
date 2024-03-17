package com.strong.BloodDonation.Security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.strong.BloodDonation.Model.Staff;
import com.strong.BloodDonation.Service.StaffService;

@Component
public class AuthProvider implements AuthenticationProvider {

    @Autowired
    private StaffService staffService;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String username = authentication.getName();
        String pwd = authentication.getCredentials().toString();
        Staff customer = staffService.findByEmail(username);
        if (customer != null) {
            if (passwordEncoder.matches(pwd, customer.getPassword())) {
                return new UsernamePasswordAuthenticationToken(username, pwd,
                        jwtUtil.getAuthorityList(customer));
            } else {
                throw new BadCredentialsException("Invalid password!");
            }
        } else {
            throw new BadCredentialsException("No user registered with this details!");
        }
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return (UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication));
    }

}
