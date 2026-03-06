package com.lab.ver2.security;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

public class CustomUserPrincipal extends User {
    private final Integer id;

    public CustomUserPrincipal(
        Integer id,
        String username,
        String password,
        Collection<? extends GrantedAuthority> authorities) {
            super(username, password, authorities);
            this.id = id;
        }

    public Integer getId(){
        return id;
    }
}