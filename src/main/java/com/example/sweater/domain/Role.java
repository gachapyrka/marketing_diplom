package com.example.sweater.domain;

import org.springframework.security.core.GrantedAuthority;

public enum Role implements GrantedAuthority {
    ADMIN,
    MARKETER,
    USER;

    @Override
    public String getAuthority() {
        return name();
    }
}
