package com.feeldip.spring.claimer.enums;

import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Set;
import java.util.stream.Collectors;

public enum Roles {
    MODERATOR(Set.of(Permission.MODERATE)),
    ADMIN(Set.of(Permission.MODERATE, Permission.ADMINISTER));

    private final Set<Permission> permissions;

    Roles(Set<Permission> permissions) {
        this.permissions = permissions;
    }

    public Set<Permission> getPermissions() {
        return permissions;
    }

    public Set<SimpleGrantedAuthority> getAuthorities(){
        return permissions.stream()
                .map(p -> new SimpleGrantedAuthority(p.getPermission()))
                .collect(Collectors.toSet());
    }
}
