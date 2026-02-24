package com.lab.ver2.security;

import java.util.List;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.*;
import org.springframework.stereotype.Service;

import com.lab.ver2.model.Usuario;
import com.lab.ver2.repository.UsuarioRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final UsuarioRepository usuarioRepository;

    @Override
    public UserDetails loadUserByUsername(String username)
            throws UsernameNotFoundException {

        Usuario usuario = usuarioRepository
                .findByUserName(username)
                .orElseThrow(() ->
                        new UsernameNotFoundException("Usuario no encontrado"));

        String role = usuario.isEsAdmin() ? "ROLE_ADMIN" : "ROLE_USER";

        return new org.springframework.security.core.userdetails.User(
                usuario.getUserName(),
                usuario.getPassword(),
                true,
                true,
                true,
                true,
                List.of(new SimpleGrantedAuthority(role))
        );
    }
}
