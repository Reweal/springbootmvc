package ru.javamentor.springbootmvc.model;

import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Set;
import java.util.stream.Collectors;

public enum Role {
    ADMIN(Set.of(Permission.USERS_WRITE, Permission.USERS_READ)),
    USER(Set.of(Permission.USERS_READ));

    Role(Set<Permission> pemissions) {
        this.pemissions = pemissions;
    }

    private final Set<Permission> pemissions;

    public Set<Permission> getPemissions() {
        return pemissions;
    }

    public Set<SimpleGrantedAuthority> getAuthorities() {
        return getPemissions().stream()
                .map(permission -> new SimpleGrantedAuthority(permission.getPermission()))
                .collect(Collectors.toSet());
    }
}
