package com.strong.BloodDonation.Security;

import com.strong.BloodDonation.Model.Staff;
import com.strong.BloodDonation.Repository.StaffRepo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class StaffAuthentication implements AuthenticationProvider {
    @Autowired
    StaffRepo staffRepo;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String name = authentication.getName();
        String password = authentication.getCredentials().toString();
        Staff staff = staffRepo.findByStaffName(name);
        if (staff == null) {
            throw new UsernameNotFoundException("User Not Found");
        }
        if (passwordEncoder.matches(password, staff.getPassword())) {
            GrantedAuthority position = getAuthorityList(staff);
            return new UsernamePasswordAuthenticationToken(staff, password, Collections.singleton(position));
        } else {
            throw new BadCredentialsException("Invalid Credentails");
        }
    }

    public GrantedAuthority getAuthorityList(Staff staff) {
        String authority = staff.getPosition();
        return new SimpleGrantedAuthority(authority);
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return (UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication));
    }
}
