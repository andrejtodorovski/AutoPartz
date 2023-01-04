package com.example.autopartz.model;

import org.springframework.security.core.GrantedAuthority;


public enum Role implements GrantedAuthority {

    ROLE_USER,ROLE_CLIENT, ROLE_ADMIN, ROLE_WAREHOUSEMAN, ROLE_DELIVERYMAN;

    @Override
    public String getAuthority() {
        return name();
    }
}

