package com.feeldip.spring.claimer.config.jwt;

import com.feeldip.spring.claimer.entity.AdminEntity;
import com.feeldip.spring.claimer.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
public class CustomAdminDetailsService implements UserDetailsService {

    @Autowired
    private AdminService adminService;

    @Override
    public CustomAdminDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return CustomAdminDetails.fromAdminEntityToCustomAdminDetails(
                adminService.getAdminByUsername(username));
    }
}
