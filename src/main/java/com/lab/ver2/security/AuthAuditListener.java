package com.lab.ver2.security;

import java.time.LocalDateTime;

import org.springframework.context.event.EventListener;
import org.springframework.security.authentication.event.*;
import org.springframework.stereotype.Component;

import com.lab.ver2.model.LoginAudit;
import com.lab.ver2.repository.LoginAuditRepository;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class AuthAuditListener {

    private final LoginAuditRepository loginAuditRepository;

    @EventListener
    public void onSuccess(AuthenticationSuccessEvent event) {
        CustomUserPrincipal user = 
            (CustomUserPrincipal) event.getAuthentication().getPrincipal();

        loginAuditRepository.save(LoginAudit.builder()
            .usuarioId(user.getId())
            .username(user.getUsername())
            .event("LOGIN_SUCCESS")
            .fechaLogin(LocalDateTime.now())
            .build());
    }

    @EventListener
    public void onFailure(AbstractAuthenticationFailureEvent event) {

        String username = (String) event.getAuthentication().getPrincipal();

        loginAuditRepository.save(LoginAudit.builder()
            .username(username)
            .event("LOGIN_FAILURE")
            .fechaLogin(LocalDateTime.now())
            .build());
    }

    @EventListener
    public void onLogout(LogoutSuccessEvent event) {

        CustomUserPrincipal user =
                (CustomUserPrincipal) event.getAuthentication().getPrincipal();

        loginAuditRepository.save(LoginAudit.builder()
            .usuarioId(user.getId())
            .username(user.getUsername())
            .event("LOGOUT")
            .fechaLogin(LocalDateTime.now())
            .build());
    }
}
