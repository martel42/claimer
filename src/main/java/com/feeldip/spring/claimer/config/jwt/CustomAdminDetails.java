package com.feeldip.spring.claimer.config.jwt;

import com.feeldip.spring.claimer.entity.AdminEntity;
import com.feeldip.spring.claimer.enums.Roles;
import com.feeldip.spring.claimer.exception.nosuch.NoSuchAdminException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.Collections;

@Component
public class CustomAdminDetails implements UserDetails {

    private String login;
    private String password;
    private Collection<? extends GrantedAuthority> grantedAuthorities;

    public static CustomAdminDetails fromAdminEntityToCustomAdminDetails(AdminEntity adminEntity){
        CustomAdminDetails customAdminDetails = new CustomAdminDetails();
        customAdminDetails.login = adminEntity.getUsernameAdmin();
        customAdminDetails.password = adminEntity.getPasswordAdmin();
        customAdminDetails.grantedAuthorities = Roles.valueOf(adminEntity.getRoleEntity().getAuthorityRole()).getAuthorities();
        return customAdminDetails;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return grantedAuthorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return login;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
